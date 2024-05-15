package com.maximde.betterchatbubbles.api;

public enum AnimationType {
    /**
     * Needs the least server performance
     * because the scale is interpolated on the clientside
     */
    SCALE,
    /*
    Needs the most server performance because the transparency
    has to be updated every tick in the animation by the server
     */
    FADE,
    PARTICLES
}
