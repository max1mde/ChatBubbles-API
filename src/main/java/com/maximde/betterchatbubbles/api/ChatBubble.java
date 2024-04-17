package com.maximde.betterchatbubbles.api;

import com.github.retrooper.packetevents.util.Vector3f;
import com.maximde.betterchatbubbles.api.utils.Vector3D;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.entity.*;

import java.util.ArrayList;
import java.util.List;

public class ChatBubble implements BubbleActions {
    @Getter
    protected int entityID;
    @Setter @Getter @Accessors(chain = true)
    protected String text = "";
    protected Vector3f scale = new Vector3f(0,0,0);
    protected Vector3f translation = new Vector3f(0,  0.7F, 0);
    @Setter @Getter @Accessors(chain = true)
    protected Display.Billboard billboard = Display.Billboard.CENTER;
    @Setter @Getter @Accessors(chain = true)
    protected int interpolationDurationTicks = 20;
    @Setter @Getter @Accessors(chain = true)
    protected double viewRange = 1;
    @Setter @Getter @Accessors(chain = true)
    protected boolean shadow = true;
    @Setter @Getter @Accessors(chain = true)
    protected int maxLineWidth = 200;
    @Setter @Getter @Accessors(chain = true)
    protected int backgroundColor;
    @Setter @Getter @Accessors(chain = true)
    protected boolean seeThroughBlocks = false;
    @Setter @Getter @Accessors(chain = true)
    protected TextDisplay.TextAlignment alignment = TextDisplay.TextAlignment.CENTER;
    @Setter @Getter @Accessors(chain = true)
    protected byte textOpacity = (byte) -1;
    @Getter
    protected final RenderMode renderMode;
    /**
     * Only these player will see the bubble if RenderMode was set to VIEWER_LIST
     */
    @Getter
    protected List<Player> viewers = new ArrayList<>();
    /**
     * Is true if the text display of the entity was killed
     */
    @Getter
    protected boolean dead = false;
    /**
     * The entity above which the chat bubble should be displayed
     */
    @Getter @Setter @Accessors(chain = true)
    protected LivingEntity target;

    @Getter
    protected Vector3D finalScale;

    public ChatBubble(LivingEntity target, RenderMode renderMode) {
        this.target = target;
        this.renderMode = renderMode;
    }

    public ChatBubble(LivingEntity target, List<Player> viewers) {
        this.target = target;
        this.viewers = viewers;
        this.renderMode = RenderMode.VIEWER_LIST;
    }

    public void addViewer(Player player) {
        this.viewers.add(player);
    }

    public void removeViewer(Player player) {
        this.viewers.remove(player);
    }

    public void removeAllViewers() {
        this.viewers.clear();
    }

    @Override
    public ChatBubble apply() {
        return this;
    }

    @Override
    public ChatBubble spawn() {
        return this;
    }

    @Override
    public ChatBubble remove() {
        return this;
    }

    public Vector3D getTranslation() {
        return new Vector3D(this.translation.x, this.translation.y, this.translation.z);
    }

    public ChatBubble setTranslation(Vector3D translation) {
        this.translation = new Vector3f(translation.x, translation.y, translation.z);
        return this;
    }

    public Vector3D getScale() {
        return new Vector3D(this.scale.x, this.scale.y, this.scale.z);
    }

    public ChatBubble setScale(Vector3D scale) {
        this.finalScale = new Vector3D(scale.x, scale.y, scale.z);
        this.scale = new Vector3f(scale.x, scale.y, scale.z);
        return this;
    }

    protected ChatBubble setCurrentScale(Vector3f scale) {
        this.scale = scale;
        return this;
    }

    public enum RenderMode {
        NONE,
        VIEWER_LIST,
        ALL,
        NEARBY
    }

}


