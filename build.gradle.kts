plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(22)) // Or use 22
    }
}

dependencies {
    testImplementation(kotlin("test"))
}



tasks.test {
    useJUnitPlatform()
}