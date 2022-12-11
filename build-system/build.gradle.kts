
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.6.21"
}

gradlePlugin {
    plugins.register("example-plugin") {
        id = "example-plugin"
        implementationClass = "org.example.ExamplePlugin"
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"
