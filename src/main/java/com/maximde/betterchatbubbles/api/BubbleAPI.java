package com.maximde.betterchatbubbles.api;

import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.Optional;
import java.util.logging.Level;

public record BubbleAPI(BubbleGenerator bubbleGenerator) {

    private static BubbleAPI bubbleAPI;

    public static void setAPI(BubbleAPI bubbleAPI) {
        BubbleAPI.bubbleAPI = bubbleAPI;
    }

    public static Optional<BubbleAPI> getBubbleAPI() {
        if (bubbleAPI == null) {
            Bukkit.getLogger().log(Level.WARNING, "Tried to access the ChatBubbles API but it was not initialized yet! Add depends 'BetterChatBubbles' to your in plugin.yml");
            return Optional.empty();
        }
        return Optional.of(bubbleAPI);
    }
}
