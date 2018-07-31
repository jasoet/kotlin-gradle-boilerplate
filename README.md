# Kotlin Gradle Boilerplate code

Boilerplate code to create gradle based Kotlin application.

## Features
- Kotlin/JVM with Kapt
- Kotlin Coroutine
- Strict Kotlin enable allWarningsAsErrors 
- Unit test with [Spek](http://spekframework.org/), JUnit 5 and [Kluent](https://markusamshove.github.io/Kluent/)
- Static code check using [Detekt](https://github.com/arturbosch/detekt) 
- Code coverage using Jacoco 
- Logging with Logback

## Requirements
### Install Java SDK 8
- Use [sdkman](http://sdkman.io/)
```sh
$ curl -s "https://get.sdkman.io" | bash
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
$ sdk version
$ sdk install java
```

### Install Gradle 4.6 or higher
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

