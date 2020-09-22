import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val coroutineVersion = "1.3.9"
val logbackVersion = "1.2.3"

val jUnitVersion = "5.6.2"
val spekVersion = "2.0.12"
val kluentVersion = "1.51"
val easyRandomVersion = "4.2.0"
val mockKVersion = "1.10.0"

plugins {
    application
    kotlin("jvm") version "1.4.0"
    id("io.gitlab.arturbosch.detekt").version("1.13.1")
    id("com.github.johnrengelman.shadow").version("6.0.0")

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
    toolVersion = "0.8.6"
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
    toolVersion = "1.13.1"
    input = files("src/main/java", "src/main/kotlin")
    parallel = true
    config = files("$rootDir/detekt.yml")
    disableDefaultRuleSets = false
    ignoreFailures = false
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

    sourceCompatibility = "11"
    targetCompatibility = "11"

    kotlinOptions {
        jvmTarget = "11"
        apiVersion = "1.4"
        languageVersion = "1.4"
        allWarningsAsErrors = true
    }
}

tasks.wrapper {
    gradleVersion = "6.6.1"
}
