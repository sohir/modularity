// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
 /*   ext {
        compose_version = '1.1.1'
    }*/
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.kotlinGradlePlugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}