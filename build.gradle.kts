buildscript {
    repositories {
        google()
        maven { url = java.net.URI.create("https://plugins.gradle.org/m2/") }
        maven { url = java.net.URI.create("https://jitpack.io") }
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
        classpath("org.jetbrains.kotlin:kotlin-android-extensions:1.5.0")
        classpath("com.google.gms:oss-licenses:0.9.2")
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
