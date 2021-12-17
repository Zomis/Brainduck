plugins {
    kotlin("multiplatform") version "1.5.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io") // KLogging
}

val jacksonVersion = "2.12.3"
val jupiterVersion = "5.7.1"

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */

    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
        withJava()
    }
    js {
        useCommonJs()

        browser {
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.5.2")
                implementation("com.github.lewik.klog:klog-jvm:2.0.2")
                implementation(kotlin("reflect"))

                implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.16.0")
                implementation("org.apache.logging.log4j:log4j-core:2.16.0")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("org.junit.jupiter:junit-jupiter-api:$jupiterVersion")
                implementation("org.junit.jupiter:junit-jupiter-params:$jupiterVersion")
                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.5.2")
                implementation("com.github.lewik.klog:klog-js:2.0.2")
            }
        }
    }
}