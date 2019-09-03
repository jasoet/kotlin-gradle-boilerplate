import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = "1.3.50"
val coroutineVersion = "1.3.0"
val logbackVersion = "1.2.3"

val jUnitVersion = "5.4.2"
val spekVersion = "2.0.5"
val kluentVersion = "1.51"
val easyRandomVersion = "4.0.0"
val mockKVersion = "1.9.3"

plugins {
    application
    kotlin("jvm") version "1.3.50"
    id("io.gitlab.arturbosch.detekt").version("1.0.1")

    jacoco
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
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
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
    testImplementation("org.amshove.kluent:kluent:$kluentVersion")
    testImplementation("io.mockk:mockk:$mockKVersion")
    testImplementation("org.jeasy:easy-random-core:$easyRandomVersion")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
}

jacoco {
    toolVersion = "0.8.4"
}

tasks.jacocoTestReport {
    group = "Reporting"
    reports {
        xml.isEnabled = true
        html.isEnabled = true
        csv.isEnabled = false
    }
}

detekt {
    version = "1.0.1"
    config = files("$rootDir/detekt.yml")
}

tasks.test {
    finalizedBy(tasks.detekt, tasks.jacocoTestReport)

    useJUnitPlatform {
        includeEngines("junit-jupiter","spek2")
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
    }
}

tasks.wrapper {
    gradleVersion = "5.6.1"
}
