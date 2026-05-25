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
        browser {
            compilerOptions {
                moduleKind.set(org.jetbrains.kotlin.gradle.dsl.JsModuleKind.MODULE_ES)
                generateTypeScriptDefinitions()
            }
        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
