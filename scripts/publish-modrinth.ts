import { readFile, readdir } from "node:fs/promises";
import path from "node:path";
import process from "node:process";

type Args = {
  version: string;
  changelogFile: string;
  projectId: string;
  token: string;
  gameVersions: string[];
  loaders: string[];
  releaseType: "release" | "beta" | "alpha";
  files: string[];
  name?: string;
};

function getArgValue(flag: string, argv: string[]): string | undefined {
  const index = argv.indexOf(flag);
  return index === -1 ? undefined : argv[index + 1];
}

function getMultiArg(flag: string, argv: string[]): string[] {
  const values: string[] = [];
  for (let index = 0; index < argv.length; index += 1) {
    if (argv[index] === flag && argv[index + 1]) {
      values.push(argv[index + 1]);
      index += 1;
    }
  }
  return values;
}

function parseArgs(argv: string[]): Args {
  const version = getArgValue("--version", argv);
  const changelogFile = getArgValue("--changelog-file", argv) ?? "CHANGELOG.md";
  const projectId = getArgValue("--project-id", argv);
  const token = process.env.MODRINTH_TOKEN ?? getArgValue("--token", argv);
  const gameVersions = (getArgValue("--game-versions", argv) ?? "").split(",").filter(Boolean);
  const loaders = (getArgValue("--loaders", argv) ?? "").split(",").filter(Boolean);
  const releaseType = (getArgValue("--release-type", argv) ?? "release") as Args["releaseType"];
  const name = getArgValue("--name", argv) ?? undefined;
  const files = getMultiArg("--file", argv);

  if (!version || !projectId || !token || gameVersions.length === 0 || loaders.length === 0 || files.length === 0) {
    throw new Error(
      "Usage: tsx scripts/publish-modrinth.ts --version <version> --changelog-file <file> --project-id <id> --game-versions <csv> --loaders <csv> --file <path> [--file <path>] [--name <name>] [--release-type release|beta|alpha]"
    );
  }

  return { version, changelogFile, projectId, token, gameVersions, loaders, releaseType, files, name };
}

async function extractChangelogSection(changelogFile: string, version: string): Promise<string> {
  const changelog = await readFile(changelogFile, "utf8");
  const lines = changelog.split(/\r?\n/);
  const startIndex = lines.findIndex((line) => line.trim() === `## [${version}]`);

  if (startIndex === -1) {
    throw new Error(`Could not find changelog section for ${version}`);
  }

  let endIndex = lines.length;
  for (let i = startIndex + 1; i < lines.length; i += 1) {
    if (lines[i].startsWith("## ")) {
      endIndex = i;
      break;
    }
  }

  const section = lines.slice(startIndex, endIndex).join("\n").trim();
  if (!section) {
    throw new Error(`Changelog section for ${version} is empty`);
  }
  return section;
}

async function findFiles(patterns: string[]): Promise<string[]> {
  const expanded: string[] = [];
  for (const pattern of patterns) {
    if (pattern.includes("*")) {
      const dir = path.dirname(pattern);
      const base = path.basename(pattern);
      const entries = await readdir(dir);
      const regex = new RegExp(`^${base.replace(/\./g, "\\.").replace(/\*/g, ".*")}$`);
      expanded.push(...entries.filter((entry) => regex.test(entry)).map((entry) => path.join(dir, entry)));
    } else {
      expanded.push(pattern);
    }
  }
  return expanded;
}

async function main() {
  const args = parseArgs(process.argv.slice(2));
  const changelog = await extractChangelogSection(args.changelogFile, args.version);
  const files = await findFiles(args.files);

  if (files.length === 0) {
    throw new Error("No release files were found");
  }

  const form = new FormData();
  const data = {
    name: args.name ?? `Version ${args.version}`,
    version_number: args.version,
    changelog,
    game_versions: args.gameVersions,
    version_type: args.releaseType,
    loaders: args.loaders,
    featured: true,
    status: "listed",
    project_id: args.projectId,
    file_parts: files.map((_, index) => `file${index}`),
    primary_file: "file0"
  };

  form.append("data", new Blob([JSON.stringify(data)], { type: "application/json" }));

  for (const [index, file] of files.entries()) {
    const bytes = await readFile(file);
    const filename = path.basename(file);
    form.append(`file${index}`, new Blob([bytes]), filename);
  }

  const response = await fetch("https://api.modrinth.com/v2/version", {
    method: "POST",
    headers: {
      Authorization: args.token
    },
    body: form
  });

  if (!response.ok) {
    throw new Error(`Modrinth publish failed: ${response.status} ${response.statusText}\n${await response.text()}`);
  }

  const created = (await response.json()) as { id?: string; name?: string };
  process.stdout.write(`Published Modrinth version ${created.name ?? args.version}${created.id ? ` (${created.id})` : ""}\n`);
}

await main();
