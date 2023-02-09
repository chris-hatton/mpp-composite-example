import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode

val iosDeploymentTarget = "15.4"

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {

    android {
        publishLibraryVariants("release", "debug")
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    setOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries {
            framework {
                embedBitcode = BitcodeEmbeddingMode.DISABLE
                baseName = project.name
            }
        }
    }

    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.example.library) // <-- COMPOSITE SUBSTITUTION SHOULD HAPPEN FOR THIS MODULE
                implementation(libs.coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            // See `android` block for dependencies
        }
        val androidTest by getting
        val desktopMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            setOf(
                iosX64Main,
                iosArm64Main,
                iosSimulatorArm64Main
            ).forEach { iosTarget ->
                iosTarget.dependsOn(this)
            }
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }

    dependencies {
        implementation(libs.android.appcompat)
    }
}
