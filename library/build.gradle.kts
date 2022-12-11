import org.example.BuildHelper

buildscript {
    dependencies {
        classpath(libs.build.androidgradleplugin)
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("example-plugin")
    alias(libs.plugins.kotlin.multiplatform)
    id("maven-publish")
    id("com.android.library") version libs.versions.build.androidgradleplugin
}

group = "org.example"
version = "1.0-SNAPSHOT"

logger.warn("Testing build-system integration: ${BuildHelper.dummyString}")

kotlin {

    android {
        publishLibraryVariants("debug", "release")
    }

    jvm("desktop")

    setOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries {
            framework {
                baseName = project.name
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    google()
}
