import com.github.gradle.node.task.NodeTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.node.install)
    kotlin("jvm") version libs.versions.kotlin
}

node {
    download.set(true)
    @Suppress("DEPRECATION")
    workDir.set(file("${project.buildDir}/nodejs"))
    @Suppress("DEPRECATION")
    npmWorkDir.set(file("${project.buildDir}/npm"))
    @Suppress("DEPRECATION")
    yarnWorkDir.set(file("${project.buildDir}/yarn"))
    nodeProjectDir.set(file(project.projectDir))
    distBaseUrl = null
}

tasks.register<NodeTask>("setupHusky") {
    description = "Sets up Husky and Commitlint hooks"
    group = "build setup"
    dependsOn("npmInstall") // Ensure npm dependencies are installed

    // Use nodeProjectDir to define the Node.js context
    val nodeDir = node.nodeProjectDir.get().asFile
    val nodeExecutable = project.layout.buildDirectory.asFile.get().resolve("nodejs")
        .resolve("node-v18.17.1-win-x64") // Update with your Node.js version
        .resolve("node.exe")
        .absolutePath

    script.set(file("husky_install.js")) // Path to the Husky setup script
    args.addAll("--node-path", nodeExecutable) // Pass the resolved Node.js path to the script

    doFirst {
        println("Using Node.js project directory: $nodeDir")
        println("Using Node.js executable: $nodeExecutable")
    }
}

tasks.register<NodeTask>("setupReleaseIt") {
    description = "Sets up Release It"
    group = "build setup"
    dependsOn("npmInstall") // Ensure npm dependencies are installed

    // Use nodeProjectDir to define the Node.js context
    val nodeDir = node.nodeProjectDir.get().asFile
    val nodeExecutable = project.layout.buildDirectory.asFile.get().resolve("nodejs")
        .resolve("node-v18.17.1-win-x64")
        .resolve("node.exe")
        .absolutePath

    script.set(file("release-it-install.js")) // Path to the Husky setup script
    args.addAll("--node-path", nodeExecutable) // Pass the resolved Node.js path to the script

    doFirst {
        println("Using Node.js project directory: $nodeDir")
        println("Using Node.js executable: $nodeExecutable")
    }
}

