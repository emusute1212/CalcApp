buildscript {
    repositories {
        google()
        maven { url = java.net.URI.create("https://plugins.gradle.org/m2/") }
        maven { url = java.net.URI.create("https://jitpack.io") }
        mavenCentral()
    }
    dependencies {
        classpath(Dep.Gradle.gradlePlugin)
        classpath(Dep.Kotlin.gradlePlugin)
        classpath(Dep.Google.ossLicensesPlugin)
        classpath(Dep.Google.googleService)
        classpath(Dep.Google.Firebase.crashlyticsGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        maven { url = java.net.URI.create("https://plugins.gradle.org/m2/") }
        maven { url = java.net.URI.create("https://jitpack.io") }
        mavenCentral()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
