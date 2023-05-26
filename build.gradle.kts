plugins {
    kotlin("jvm") version "1.8.20"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Needed for lightweight threads "coroutines"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    // Needed for Kotlin reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.21")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}