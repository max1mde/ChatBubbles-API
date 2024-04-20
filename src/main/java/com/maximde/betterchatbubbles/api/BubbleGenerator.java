package com.maximde.betterchatbubbles.api;

import com.maximde.betterchatbubbles.api.ChatBubble;
import org.bukkit.entity.LivingEntity;


public interface BubbleGenerator {
    void addBubble(LivingEntity target, String text);

    void addBubble(ChatBubble chatBubble);
    /**
     * @param target the target entity above which the chat bubbles are displayed
     */
    void clearBubbles(LivingEntity target);

    /**
     * Get the amount of currently shown bubbles above a specific entity
     */
    int getBubbleAmount(LivingEntity target);
}
