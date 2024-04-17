<div align="center">
<img width="900px" src="https://github.com/max1mde/ChatBubbles-API/assets/114857048/d27018df-972d-4796-8a5d-6eab02f05f89"><br>
<a href="https://jitpack.io/#max1mde/ChatBubbles-API">
<img src="https://jitpack.io/v/max1mde/ChatBubbles-API.svg">
</a>
<h1>The official api of the plugin better chat bubbles</h1>
</div>

# Gradle

## Groovy
```groovy
repositories {
	maven { url 'https://jitpack.io' }
}
    
dependencies {
	compileOnly 'com.github.max1mde:ChatBubbles-API:1.3.0'
}
```

## Kotlin DSL
```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.max1mde:ChatBubbles-API:1.3.0")
}
```

# Maven
  
```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
  
<dependency>
	<groupId>com.github.max1mde</groupId>
	<artifactId>ChatBubbles-API</artifactId>
	<version>1.3.0</version>
        <scope>provided</scope>
</dependency>
```
