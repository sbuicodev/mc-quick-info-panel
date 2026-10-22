pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/") { name = "FabricMC" }
        maven("https://maven.neoforged.net/releases/") { name = "NeoForged" }
        maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
    }
}

plugins {
    // See https://stonecutter.kikugie.dev/ for new versions
    id("dev.kikugie.stonecutter") version "0.9.8"
    // Applies fabric-loom-remap before 26.1 and fabric-loom from 26.1 (https://codeberg.org/KikuGie/loom-back-compat)
    id("dev.kikugie.loom-back-compat") version "0.4.2"
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

stonecutter {
    create(rootProject) {
        // Creates `versions/{version}-{loader}` nodes, built with `build.{loader}.gradle.kts`.
        // Each node is named after the lowest Minecraft version it builds against; the full
        // list of versions a jar supports is `mod.mc_releases` in stonecutter.properties.toml.
        fun match(version: String, vararg loaders: String) {
            for (loader in loaders) version("$version-$loader", version).buildscript("build.$loader.gradle.kts")
        }

        match("1.21", "fabric", "neoforge")
        match("1.21.2", "fabric", "neoforge")
        match("1.21.4", "fabric", "neoforge")
        match("1.21.5", "fabric", "neoforge")
        match("1.21.6", "fabric", "neoforge")
        match("1.21.9", "fabric", "neoforge")
        match("1.21.11", "fabric", "neoforge")
        match("26.1", "fabric", "neoforge")
        match("26.2", "fabric", "neoforge")
        match("26.3", "fabric", "neoforge")
        vcsVersion = "26.3-fabric"
    }
}

// This should match the folder name of the project, or else IDEA may complain (see https://youtrack.jetbrains.com/issue/IDEA-317606)
rootProject.name = "mc-quick-info-panel"
