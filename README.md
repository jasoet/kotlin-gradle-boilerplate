# Kotlin Gradle Boilerplate code

Boilerplate code to create gradle based Kotlin application.

## Features
- Kotlin/JVM with kapt
- Kotlin Coroutine
- Strict Kotlin enable allWarningsAsErrors 
- [Arrow Library](http://arrow-kt.io/)
- Unit test with Kotlin test and JUnit 4
- Static code check using [Detekt](https://github.com/arturbosch/detekt) 
- Code coverage using Jacoco 

## Requirements
### Install Java SDK 8
- Use [sdkman](http://sdkman.io/)
```sh
$ curl -s "https://get.sdkman.io" | bash
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
$ sdk version
$ sdk install java
```

## Usage
```sh
$ git clone git@github.com:jasoet/kotlin-gradle-boilerplate.git ${YOUR_PROJECT_NAME}
$ cd ${YOUR_PROJECT_NAME}
$ ./gradlew clean build
```

