tasks.register<Copy>("copyWorkerCode") {
    dependsOn(":worker:jsBrowserProductionLibraryDistribution")

    from("worker/build/dist/js/productionLibrary")
    into("../frontend/public/worker")
    include("*.*")
}

tasks.register<Copy>("copyAppCode") {
    dependsOn(":app:jsBrowserProductionLibraryDistribution")

    from("app/build/dist/js/productionLibrary")
    into("../frontend/src/app")
    include("*.*")
}

tasks.register("dist") {
    dependsOn("copyWorkerCode")
    dependsOn("copyAppCode")
}
