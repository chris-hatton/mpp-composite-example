import org.example.BuildHelper

plugins {
    // 'Versions' and 'Updates' plugins enable analysis and semi-automatic update of the project's dependencies
    id("example-plugin")
    alias(libs.plugins.versions)
    alias(libs.plugins.updates)
}

buildscript {
    dependencies {
        classpath(libs.build.androidgradleplugin)
    }
}

logger.warn("Testing build-system integration: ${BuildHelper.dummyString}")
