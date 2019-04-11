# Kotlin Gradle Boilerplate code

[![Build Status](https://travis-ci.org/jasoet/kotlin-gradle-boilerplate.svg?branch=master)](https://travis-ci.org/jasoet/kotlin-gradle-boilerplate)

Boilerplate code for gradle based Kotlin application.

## Features
- Kotlin/JVM 
- Kotlin Coroutine
- Strict Kotlin enable allWarningsAsErrors 
- Unit test with [Spek2](http://spekframework.org/), JUnit 5, [Kluent](https://markusamshove.github.io/Kluent/) and [MockK](https://mockk.io/)
- Static code check using [Detekt](https://github.com/arturbosch/detekt) 
- Code coverage using Jacoco 
- Logging with Logback
- Gradle Kotlin DSL

## Requirements
### Install Java SDK 11
- Use [sdkman](http://sdkman.io/)
```sh
$ curl -s "https://get.sdkman.io" | bash
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
$ sdk version
$ sdk install java
```

### Install Gradle 5.3 or higher
```sh
$ sdk update
$ sdk install gradle
```

## Usage
```sh
$ git clone git@github.com:jasoet/kotlin-gradle-boilerplate.git ${YOUR_PROJECT_NAME}
$ cd ${YOUR_PROJECT_NAME}
$ ./gradlew clean build
```

