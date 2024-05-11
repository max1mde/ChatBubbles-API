package com.maximde.betterchatbubbles.api;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.List;


public interface BubbleGenerator {
    void spawnBubble(LivingEntity target, String text);
    void spawnBubble(LivingEntity target, String text, boolean triggerEvent);

    /**
     * Can be used for non-existing entities like NPC's
     * @param entityID the entity ID of the target entity (Not the UUID)
     * @param entityLocation the location of the target entity
     * @param text the text displayed in the chat bubble
     */
    void spawnBubble(int entityID, Location entityLocation, String text);
    /**
     * Can be used for non-existing entities like NPC's
     * @param entityID the entity ID of the target entity (Not the UUID)
     * @param entityLocation the location of the target entity
     * @param text the text displayed in the chat bubble
     * @param triggerEvent if true the BubbleCreateEvent will be called
     */
    void spawnBubble(int entityID, Location entityLocation, String text, boolean triggerEvent);
    void spawnBubble(ChatBubble chatBubble);
    void spawnBubble(ChatBubble chatBubble, boolean triggerEvent);
    /**
     * @param target the target entity above which the chat bubbles are displayed
     */
    void clearBubbles(LivingEntity target);

    /**
     * Get the amount of currently shown bubbles above a specific entity
     */
    int getBubbleAmount(LivingEntity target);

    List<ChatBubble> getActiveBubbles(LivingEntity target);

    List<ChatBubble> getActiveBubbles(int entityID);

    List<ChatBubble> getAllActiveBubbles();

    /**
     * If you want to use the bubble actions before you spawned it using a BubbleGenerator#spawnBubble(..) method,
     * you have to initialize the actions first.
     * Or if you've overwritten the implementation of the actions you can reset them using this method
     * @param chatBubble
     */
    void initBubbleActions(ChatBubble chatBubble);
}
