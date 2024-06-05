<div align="center">
<img width="900px" src="https://github.com/max1mde/ChatBubbles-API/assets/114857048/05d526ac-11aa-4cc2-870e-2fa385f90cee"><br>
<a href="https://jitpack.io/#max1mde/ChatBubbles-API">
<img src="https://jitpack.io/v/max1mde/ChatBubbles-API.svg">
</a>
<h1>The official api of the plugin Better Chat Bubbles</h1>
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
	compileOnly 'com.github.max1mde:ChatBubbles-API:1.5.3'
}
```
Kotlin DSL
```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.max1mde:ChatBubbles-API:1.5.3")
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
	<version>1.5.3</version>
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

ChatBubble chatBubble = new ChatBubble(target, RenderMode.NEARBY); // RenderMode nearby means all players near the target will see the chat bubbles

// Now you can modify the chat bubble
chatBubble.setText("Test")
    .setScale(new Vector3D(2,2,2))
    .setShadow(true)
    .setBillboard(Display.Billboard.VERTICAL)
    .setMaxLineWidth(500)
    .setSeeThroughBlocks(false);


// There are also the bubble actions:

// Spawns the chat bubble entity
chatBubble.getActions().spawn();
// Applies your changed properties of the chatBubble object to the entity
chatBubble.getActions().apply();
// Removes the entity
chatBubble.getActions().remove();

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
bubbleAPI.getBubbleGenerator().spawnBubble(chatBubble);
```
You can get all active chat bubbles

```java
List<ChatBubble> activeChatBubbles = bubbleAPI.getBubbleGenerator().getAllActiveBubbles();
```
or all active chat bubbles of a specific entity
```java
List<ChatBubble> activeChatBubbles = bubbleAPI.getBubbleGenerator().getActiveBubbles(targetEntity);
```

There are also some other methods in the bubble generator

```java
// You dont need a ChatBubble object for this method it will just take the values from the config for the bubble
bubbleAPI.getBubbleGenerator().spawnBubble(target, "The text in the chat bubble");

// Remove all bubbles of an entity without any animation
bubbleAPI.getBubbleGenerator().clearBubbles(target);

// Get the currently shown/active chat bubbles of an entity
int currentBubbleAmount = bubbleAPI.getBubbleGenerator().getBubbleAmount(target)

// Get by entity ID
List<ChatBubble> activeBubblesFromEntity = bubbleAPI.getBubbleGenerator().getActiveBubbles(int entityID);

/**
* If you want to use the bubble actions before you spawned it using a BubbleGenerator#spawnBubble(..) method,
* you have to initialize the actions first.
* Or if you've overwritten the implementation of the actions you can reset them using this method
*/
bubbleAPI.getBubbleGenerator().initBubbleActions(ChatBubble chatBubble);
```

--------------------------

# Examples

A simple damage indicator:

```java
@EventHandler
public void onEntityDamage(EntityDamageEvent event) {
    if(!(event.getEntity() instanceof LivingEntity)) return;
    ChatBubble chatBubble = new ChatBubble((LivingEntity) event.getEntity(), RenderMode.NEARBY);

    chatBubble.setText(ChatColor.RED + "Damage: " + event.getDamage())
            .setScale(new Vector3D(1,1,1));

    BubbleAPI.getBubbleAPI().get().bubbleGenerator().spawnBubble(chatBubble);
}
```
