rootProject.name = "Brainduck"

pluginManagement {
    repositories {
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(":shared")
include(":worker")
include(":app")
