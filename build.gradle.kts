import com.github.gradle.node.npm.task.NpmTask

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

tasks.register<NpmTask>("huskyInstall") {
    description = "Installs Husky Git hooks"
    group = "build setup"
    dependsOn("npmInstall")
    workingDir.set(project.rootDir)
    args.set(listOf("run", "prepare"))
}

tasks.register("setHuskyHookPermissions") {
    doLast {
        val commitMsgHook = file(".husky/commit-msg")
        if (commitMsgHook.exists()) {
            commitMsgHook.setExecutable(true)
            println("Set executable permissions for .husky/commit-msg")
        }
    }
}

tasks.named("huskyInstall") {
    finalizedBy("setHuskyHookPermissions")
}