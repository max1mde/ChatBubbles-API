<div align="center">
<img width="900px" src="https://github.com/max1mde/ChatBubbles-API/assets/114857048/05d526ac-11aa-4cc2-870e-2fa385f90cee"><br>
<a href="https://jitpack.io/#max1mde/ChatBubbles-API">
<img src="https://jitpack.io/v/max1mde/ChatBubbles-API.svg">
</a>
<h1>The official api of the plugin better chat bubbles</h1>
<p>Remove, create new or modify existing chat bubbles on any living entity as you like</p>
</div>


- [API Setup](#API-Setup)
  - [Dependency](#Dependency)
    - [Gradle](#Gradle)
    - [Maven](#Maven)
- [Usage](#Usage)
  - [Events](#Events)
  - [ChatBubble](#ChatBubble)
  - [Bubble generator](#Bubble-generator)
- [Examples](#Examples)

----------------------------------------------------------

# API Setup

> [!IMPORTANT]  
> Add depend `BetterChatBubbles`  
> in your `plugin.yml` file!

For example like that:
```yml
name: ChatBubblesExample
version: '${version}'
main: com.maximde.chatbubblesexample.ChatBubblesExample
api-version: '1.19'

depend:
  - BetterChatBubbles
```


## Dependency

### Gradle
Groovy
```groovy
repositories {
	maven { url 'https://jitpack.io' }
}
    
dependencies {
	compileOnly 'com.github.max1mde:ChatBubbles-API:1.3.2'
}
```
Kotlin DSL
```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.max1mde:ChatBubbles-API:1.3.2")
}
```

### Maven
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
	<version>1.3.2</version>
        <scope>provided</scope>
</dependency>
```

# Usage
How to use the API in your plugin

## Events
--- 
The **BubbleCreateEvent**  
Import:
`com.maximde.betterchatbubbles.api.events.BubbleCreateEvent`


Can be used like that:
```java
@EventHandler
public void onBubbleCreate(BubbleCreateEvent event) {
    ChatBubble chatBubble = event.getChatBubble();
    // You can modify the chat bubble
    chatBubble.setScale(new Vector3D(5, 5, 5));

    // You can also cancel the event (So this chat bubble will not be created)
    event.setCancelled(true);
}
```

--- 

The **BubbleRemoveEvent**  
Import:
`com.maximde.betterchatbubbles.api.events.BubbleRemoveEvent`


Can be used like that:
```java
@EventHandler
public void onBubbleRemove(BubbleRemoveEvent event) {
    ChatBubble chatBubble = event.getChatBubble();
    // You can get the chat bubble and the data in it.

    /* You can cancel the event which stops the bubble from 'despawning'
     * (This means the bubble will never be removed until the viewer rejoins the server)
     */
    event.setCancelled(true);
}
```
---

## ChatBubble

Import `com.maximde.betterchatbubbles.api.ChatBubble`

Creating a new instance of the ChatBubble's class
```java

LivingEntity target = // This can be any player or living entity like a zombie! Above this entity the chat bubbles will be shown!

ChatBubble chatBubble = new ChatBubble(target, ChatBubble.RenderMode.NEARBY); // RenderMode nearby means all players near the target will see the chat bubbles

// Now you can modify the chat bubble
chatBubble.setText("Test")
    .setScale(new Vector3D(2,2,2))
    .setShadow(true)
    .setBillboard(Display.Billboard.VERTICAL)
    .setMaxLineWidth(500)
    .setSeeThroughBlocks(false);
// For more properties look into the ChatBubble class or in your IDE's auto completions for that object 
```

To spawn that chat bubble you need the bubble generator ↓

## Bubble generator

First you need to get the instance of the API:

```java
BubbleAPI bubbleAPI = BubbleAPI.getBubbleAPI().get();
```

The you can use the bubble generator like that:

```java
bubbleAPI.getBubbleGenerator().addBubble(chatBubble);
```

There are also some other methods in the bubble generator

```java
// You dont need a ChatBubble object for this method it will just take the values from the config for the bubble
bubbleAPI.getBubbleGenerator().addBubble(target, "The text in the chat bubble");

// Remove all bubbles of an entity without any animation
bubbleAPI.getBubbleGenerator().clearBubbles(target);

// Get the currently shown/active chat bubbles of an entity
int currentBubbleAmount = bubbleAPI.getBubbleGenerator().getBubbleAmount(target)
```

--------------------------

# Examples

A simple damage indicator:

```java
@EventHandler
public void onEntityDamage(EntityDamageEvent event) {
    if(!(event.getEntity() instanceof LivingEntity)) return;
    ChatBubble chatBubble = new ChatBubble((LivingEntity) event.getEntity(), ChatBubble.RenderMode.NEARBY);

    chatBubble.setText(ChatColor.RED + "Damage: " + event.getDamage())
            .setScale(new Vector3D(1,1,1));

    BubbleAPI.getBubbleAPI().get().bubbleGenerator().addBubble(chatBubble);
}
```
