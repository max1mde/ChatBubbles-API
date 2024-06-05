package com.maximde.betterchatbubbles.api;

import com.maximde.betterchatbubbles.api.utils.ItemsAdderHolder;
import com.maximde.betterchatbubbles.api.utils.ReplaceText;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.Optional;
import java.util.logging.Level;

public record BubbleAPI(BubbleGenerator bubbleGenerator) {

    private static BubbleAPI bubbleAPI;

    private static ReplaceText replaceText;

    public static void setAPI(BubbleAPI bubbleAPI) {
        BubbleAPI.bubbleAPI = bubbleAPI;
        try {
            replaceText = new ItemsAdderHolder();
        } catch (ClassNotFoundException exception) {
            replaceText =  new ReplaceText() {
                @Override
                public String replace(String s) {
                    return s;
                }
            };
        }
    }

    public static Optional<BubbleAPI> getBubbleAPI() {
        if (bubbleAPI == null) {
            Bukkit.getLogger().log(Level.WARNING, "Tried to access the ChatBubbles API but it was not initialized yet! Add depends 'BetterChatBubbles' to your in plugin.yml");
            return Optional.empty();
        }

        return Optional.of(bubbleAPI);
    }

    public ReplaceText getReplaceText() {
        return replaceText;
    }
}
