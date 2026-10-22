plugins {
    id("dev.kikugie.stonecutter")
    // see https://projects.neoforged.net/neoforged/moddevgradle for new versions
    id("net.neoforged.moddev") version "2.0.148" apply false
    // Publishing, see https://plugins.gradle.org for new versions
    id("com.modrinth.minotaur") version "2.10.0" apply false
    id("net.darkhax.curseforgegradle") version "1.3.33" apply false
}

stonecutter active "26.3-fabric"

stonecutter parameters {
    val version = current.project.substringBeforeLast('-')
    val loader = current.project.substringAfterLast('-')

    // Applies the version- and loader-specific sections of stonecutter.properties.toml
    properties {
        tags(version, loader)
    }

    // `//? if fabric {` and `//? if neoforge {` in sources
    constants {
        match(loader, "fabric", "neoforge")
    }

    // Class renames that need no comments in the sources, which use the newest names.
    // Whole words only, so method names like NeoForge's getGuiGraphics() stay untouched.
    replacements {
        regex(current.parsed >= "1.21.11") {
            replace("\\bResourceLocation\\b" to "Identifier", "\\bIdentifier\\b" to "ResourceLocation")
        }
        regex(current.parsed >= "26.1") {
            replace("\\bGuiGraphics\\b" to "GuiGraphicsExtractor", "\\bGuiGraphicsExtractor\\b" to "GuiGraphics")
        }
        // Default key bindings use SDL key codes from 26.3, GLFW before
        string(current.parsed >= "26.3") {
            replace("org.lwjgl.glfw.GLFW", "org.lwjgl.sdl.SDLKeycode")
        }
        regex(current.parsed >= "26.3") {
            replace("\\bGLFW\\.GLFW_KEY_(\\w+)" to "SDLKeycode.SDLK_$1", "\\bSDLKeycode\\.SDLK_(\\w+)" to "GLFW.GLFW_KEY_$1")
        }
    }
}

// Publish one node at a time, oldest version first, so the newest uploads end up on top
stonecutter tasks {
    order("modrinth")
    order("curseforge")
}
