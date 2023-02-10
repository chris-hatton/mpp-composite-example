
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    application
}

application {
    mainClass.set("MainKt")
}

kotlin {
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val desktopMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(libs.coroutines.core)
            }
        }
        val desktopTest by getting
    }
}
