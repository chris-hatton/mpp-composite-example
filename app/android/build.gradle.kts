@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
    alias(libs.plugins.kotlin.multiplatform)
}

group = "org.example"
version = "1.0-SNAPSHOT"

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(libs.example.library)
                
                implementation(libs.android.appcompat)

                implementation(libs.ktor.client.logging)
            }
        }
    }
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "org.example.app"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
