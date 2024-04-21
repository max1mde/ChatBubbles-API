package com.maximde.betterchatbubbles.api;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;


public interface BubbleGenerator {
    void addBubble(LivingEntity target, String text);
    void addBubble(LivingEntity target, String text, boolean triggerEvent);

    /**
     * Can be used for non-existing entities like NPC's
     * @param entityID the entity ID of the target entity (Not the UUID)
     * @param entityLocation the location of the target entity
     * @param text the text displayed in the chat bubble
     */
    void addBubble(int entityID, Location entityLocation, String text);
    /**
     * Can be used for non-existing entities like NPC's
     * @param entityID the entity ID of the target entity (Not the UUID)
     * @param entityLocation the location of the target entity
     * @param text the text displayed in the chat bubble
     * @param triggerEvent if true the BubbleCreateEvent will be called
     */
    void addBubble(int entityID, Location entityLocation, String text, boolean triggerEvent);
    void addBubble(ChatBubble chatBubble);
    void addBubble(ChatBubble chatBubble, boolean triggerEvent);
    /**
     * @param target the target entity above which the chat bubbles are displayed
     */
    void clearBubbles(LivingEntity target);

    /**
     * Get the amount of currently shown bubbles above a specific entity
     */
    int getBubbleAmount(LivingEntity target);
}
