plugins {
    kotlin("multiplatform") version "2.2.0"
}

kotlin {
    js(IR) {
        nodejs {
            testTask {
                useMocha()
            }
        }
        compilerOptions {
            moduleKind.set(org.jetbrains.kotlin.gradle.dsl.JsModuleKind.MODULE_ES)
            generateTypeScriptDefinitions()
        }
        browser()
        binaries.library()
    }
    jvm { }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
            }
        }
    }
}
