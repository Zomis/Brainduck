plugins {
    kotlin("multiplatform") version "2.2.0"
    kotlin("plugin.serialization") version "2.2.0"
}

tasks.register<Copy>("dist") {
    dependsOn(":app:jsBrowserProductionLibraryDistribution")
    dependsOn(":worker:jsBrowserProductionLibraryDistribution")

    from("worker/build/dist/js/productionLibrary")
    into("../frontend/public/worker")
    include("*.*")
}

tasks.register<Copy>("distApp") {
    dependsOn("dist")

    from("app/build/dist/js/productionLibrary")
    into("../frontend/src/app")
    include("*.*")
}