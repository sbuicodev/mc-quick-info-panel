plugins {
    // Applies fabric-loom-remap (Mojang names, remapped jar) before 26.1 and fabric-loom from 26.1
    id("dev.kikugie.loom-back-compat")
    id("com.modrinth.minotaur")
    id("net.darkhax.curseforgegradle")
}

val loader = "fabric"
val modId = property("mod.id") as String
val modName = property("mod.name") as String
val modVersion = property("mod.version") as String
val modAuthor = property("mod.author") as String
val modLicense = property("mod.license") as String
val modCredits = property("mod.credits") as String
val modDescription = property("mod.description") as String
val modrinthProjectId = property("publish.modrinth") as String
val curseforgeProjectId = property("publish.curseforge") as String
// Every Minecraft version this jar supports, e.g. [1.21.6, 1.21.7, 1.21.8], and their label, e.g. 1.21.6-1.21.8
val mcReleases: List<String> = sc.properties.rawOrNull("mod", "mc_releases")?.asList().orEmpty().map { it.toString() }
val mcLabel = if (mcReleases.size == 1) mcReleases.first() else "${mcReleases.first()}-${mcReleases.last()}"
val fabricLoaderVersion = property("deps.fabric_loader") as String
val requiredJava = if (sc.current.parsed >= "26.1") JavaVersion.VERSION_25 else JavaVersion.VERSION_21

// Full version, used for the jar name, mod metadata and upload titles: 1.3.1+fabric-26.3
version = "$modVersion+$loader-$mcLabel"
base.archivesName = modId

repositories {
    maven("https://maven.terraformersmc.com/releases/") { name = "Terraformers" }
}

dependencies {
    minecraft("com.mojang:minecraft:${sc.current.version}")
    // Mojang names on obfuscated versions, a no-op from 26.1
    loomx.applyMojangMappings()
    // `mod*` configurations work on 26.1+ too, loom-back-compat maps them
    modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
    modImplementation("com.terraformersmc:modmenu:${property("deps.modmenu")}")

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    withSourcesJar()
    toolchain.languageVersion = JavaLanguageVersion.of(requiredJava.majorVersion)
}

tasks {
    processResources {
        val props = mapOf(
            "version" to version.toString(),
            "mod_id" to modId,
            "mod_name" to modName,
            "mod_author" to modAuthor,
            "license" to modLicense,
            "credits" to modCredits,
            "description" to modDescription.replace("\n", "\\n"),
            "java_version" to requiredJava.majorVersion,
            "fabric_loader_version" to fabricLoaderVersion,
            // >=1.21.6 <=1.21.8
            "fabric_minecraft_version_range" to ">=${mcReleases.first()} <=${mcReleases.last()}",
        )
        inputs.properties(props)
        filesMatching(listOf("pack.mcmeta", "fabric.mod.json", "*.mixins.json")) { expand(props) }
        exclude("META-INF/neoforge.mods.toml")
    }

    jar {
        from(rootProject.file("LICENSE")) { rename { "${it}_$modName" } }
        manifest.attributes(
            "Specification-Title" to modName,
            "Specification-Vendor" to modAuthor,
            "Specification-Version" to archiveVersion,
            "Implementation-Title" to loader,
            "Implementation-Version" to archiveVersion,
            "Implementation-Vendor" to modAuthor,
            "Built-On-Minecraft" to sc.current.version,
        )
    }

    named<Jar>("sourcesJar") {
        from(rootProject.file("LICENSE")) { rename { "${it}_$modName" } }
    }

    test {
        useJUnitPlatform()
        maxHeapSize = "1G"
    }

    // Collects the mod jar into build/release at the root, for the release workflow
    register<Copy>("buildAndCollect") {
        group = "build"
        from(loomx.modJar.flatMap { it.archiveFile })
        into(rootProject.layout.buildDirectory.dir("release"))
    }
}

// Publishing, run by the release workflow (.github/workflows/release.yml)
// The full changelog lives on the Fabric upload; NeoForge only points here.
val releaseNotes = rootProject.file("release-notes.md")
val changelogText = if (releaseNotes.exists()) releaseNotes.readText() else ""

modrinth {
    token = System.getenv("MODRINTH_TOKEN")
    projectId = modrinthProjectId
    versionNumber = version.toString()
    versionName = "$modName $version"
    versionType = "release"
    uploadFile.set(loomx.modJar)
    gameVersions.set(mcReleases)
    loaders.set(listOf(loader))
    changelog = changelogText
    dependencies {
        required.project("fabric-api")
        optional.project("modmenu")
    }
}

tasks.register<net.darkhax.curseforgegradle.TaskPublishCurseForge>("curseforge") {
    apiToken = System.getenv("CURSEFORGE_TOKEN")
    val mainFile = upload(curseforgeProjectId, loomx.modJar)
    mainFile.displayName = "$modName $version"
    mainFile.releaseType = "release"
    mainFile.changelog = changelogText
    mainFile.changelogType = "markdown"
    mainFile.addModLoader("Fabric")
    mcReleases.forEach { mainFile.addGameVersion(it) }
    mainFile.addJavaVersion("Java ${requiredJava.majorVersion}")
    mainFile.addRequirement("fabric-api")
    mainFile.addOptional("modmenu")
}
