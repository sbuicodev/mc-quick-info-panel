import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters

// Keeps NeoForge from setting up Minecraft for several versions at once, which can exhaust memory
interface NeoForgeMutex : BuildService<BuildServiceParameters.None>

val mutex = gradle.sharedServices.registerIfAbsent("createMinecraftArtifactsMutex", NeoForgeMutex::class.java) {
    maxParallelUsages.set(1)
}

tasks.named { it == "createMinecraftArtifacts" }.configureEach {
    usesService(mutex)
}
