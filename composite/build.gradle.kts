
buildscript {
    dependencies {
        classpath(libs.build.androidgradleplugin)
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
}

group = "org.test"
version = "1.0-SNAPSHOT"
