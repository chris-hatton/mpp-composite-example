
// Used to add repositories to both the plugin and dependency management configurations
fun RepositoryHandler.configureCommonRepositories() {
    google()
    mavenCentral()
}

pluginManagement.repositories {
    gradlePluginPortal()
    configureCommonRepositories()
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        configureCommonRepositories()
    }
}

//rootProject.name = "app"

includeBuild("../build-system")
include(
    "shared",
    "android",
    "desktop",
)
// The iOS project should be opened in Xcode
