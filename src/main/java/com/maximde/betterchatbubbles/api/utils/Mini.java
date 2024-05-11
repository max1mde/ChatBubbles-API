package com.maximde.betterchatbubbles.api.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Mini {
    private static MiniMessage minimessage = MiniMessage.miniMessage();
    public static Component message(String message) {
        return minimessage.deserialize(message);
    }
}
