import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = "1.3.11"
val coroutineVersion = "1.0.0"
val serializationVersion = "0.9.0"
val arrowVersion = "0.7.3"
val jUnitVersion = "5.3.1"
val spekVersion = "2.0.0-rc.1"
val ktorVersion = "1.0.0"
val kluentVersion = "1.4"
val mockKVersion = "1.8.13.kotlin13"
val logbackVersion = "1.2.3"
val experimentalFlags = listOf(
    "-Xuse-experimental=kotlin.Experimental",
    "-Xuse-experimental=kotlin.ExperimentalMultiplatform",
    "-Xuse-experimental=kotlinx.serialization.ImplicitReflectionSerializer"
)

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.3.11")
    }
}

plugins {
    application
    kotlin("jvm") version "1.3.11"
    kotlin("kapt") version "1.3.11"
    id("io.gitlab.arturbosch.detekt").version("1.0.0.RC8")
    jacoco
    idea
}

apply {
    plugin("kotlinx-serialization")
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/kotlinx") }
}

group = "id.jasoet.boilerplate"
version = "1.0.0"

application {
    mainClassName = "id.jasoet.boilerplate.Application"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.arrow-kt:arrow-core:$arrowVersion")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.amshove.kluent:kluent:$kluentVersion")
    testImplementation("io.mockk:mockk:$mockKVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion") {
        exclude(group = "org.jetbrains.kotlin")
    }
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion") {
        exclude(group = "org.junit.platform")
        exclude(group = "org.jetbrains.kotlin")
    }
}

jacoco {
    toolVersion = "0.8.2"
}

tasks.jacocoTestReport {
    group = "Reporting"
    reports {
        xml.isEnabled = true
        html.isEnabled = false
        csv.isEnabled = false
    }
}

detekt {
    version = "1.0.0.RC9.2"
    profile("main") {
        input = "$projectDir/src/main/kotlin"
        config = "$rootDir/detekt.yml"
        filters = ".*test.*,.*/resources/.*,.*/tmp/.*"
    }
}

tasks.test {
    finalizedBy(tasks.detektCheck, tasks.jacocoTestReport)

    useJUnitPlatform {
        includeEngines("spek2")
    }

    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events("passed", "failed", "skipped")
    }
}

tasks.withType<KotlinCompile> {

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    kotlinOptions {
        jvmTarget = "1.8"
        apiVersion = "1.3"
        languageVersion = "1.3"
        allWarningsAsErrors = true
        freeCompilerArgs += experimentalFlags
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true

        sourceDirs.add(File("build/generated/source/kapt/main"))
        testSourceDirs.add(File("build/generated/source/kapt/test"))

        sourceDirs.add(File("build/generated/source/kaptKotlin/main"))
        testSourceDirs.add(File("build/generated/source/kaptKotlin/test"))

        sourceDirs.add(File("build/tmp/kapt/main/kotlinGenerated"))
        testSourceDirs.add(File("build/tmp/kapt/test/kotlinGeneratedst"))
    }
}

tasks.wrapper {
    gradleVersion = "5.0"
}
