package com.maximde.betterchatbubbles.api;

import com.github.retrooper.packetevents.util.Vector3f;
import com.maximde.betterchatbubbles.api.utils.Mini;
import com.maximde.betterchatbubbles.api.utils.Vector3D;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.*;

import java.util.ArrayList;
import java.util.List;

public class ChatBubble {
    /**
     * Don't use the setter without a good reason!
     */
    @Getter @Setter
    protected int entityID;
    protected Component text = Component.text("");
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
    /**
     * If true the target entity ID will be used for everything instead of the target!
     */
    @Setter @Getter @Accessors(chain = true)
    protected boolean useTargetEntityID;
    @Setter @Getter @Accessors(chain = true)
    protected boolean seeThroughBlocks = false;
    @Setter @Getter @Accessors(chain = true)
    protected TextDisplay.TextAlignment alignment = TextDisplay.TextAlignment.CENTER;
    @Setter @Getter @Accessors(chain = true)
    protected byte textOpacity = (byte) -1;
    @Getter
    public final RenderMode renderMode;
    /**
     * Only these player will see the bubble if RenderMode was set to VIEWER_LIST
     */
    @Getter
    protected List<Player> viewers = new ArrayList<>();
    /**
     * Is true if the text display of the entity was killed
     * Don't use the setter without a good reason.
     */
    @Getter @Setter
    protected boolean dead = false;
    /**
     * The entity above which the chat bubble should be displayed
     */
    @Getter @Setter @Accessors(chain = true)
    protected LivingEntity target;

    @Getter @Setter @Accessors(chain = true)
    protected int targetEntityID;

    @Getter @Setter @Accessors(chain = true)
    protected Location targetEntityLocation;

    @Getter
    protected Vector3D finalScale;

    @Setter @Getter @Accessors(chain = true)
    protected String textColor = "#FFFFFF";

    /**
     * The duration for the animation in ticks
     */
    @Setter @Getter @Accessors(chain = true)
    protected int animationUpDuration = 5;

    /**
     * The duration for the animation in ticks
     */
    @Setter @Getter @Accessors(chain = true)
    protected int fadeInOutDuration = 5;

    /**
     * Don't use the setter if you have no good reason.
     */
    @Getter @Setter
    protected BubbleActions actions;

    public ChatBubble(LivingEntity target, RenderMode renderMode) {
        this.target = target;
        this.renderMode = renderMode;
    }

    public ChatBubble(LivingEntity target, List<Player> viewers) {
        this.target = target;
        this.viewers = viewers;
        this.renderMode = RenderMode.VIEWER_LIST;
    }

    public ChatBubble(int entityID, Location entityLocation, RenderMode renderMode) {
        this.targetEntityID = entityID;
        this.renderMode = renderMode;
        this.useTargetEntityID = true;
        this.targetEntityLocation = entityLocation;
    }

    public ChatBubble(int entityID, Location entityLocation, List<Player> viewers) {
        this.targetEntityID = entityID;
        this.viewers = viewers;
        this.renderMode = RenderMode.VIEWER_LIST;
        this.useTargetEntityID = true;
        this.targetEntityLocation = entityLocation;
    }

    public void addAllViewers(List<Player> viewerList) {
        this.viewers.addAll(viewerList);
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

    public Vector3D getTranslation() {
        return new Vector3D(this.translation.x, this.translation.y, this.translation.z);
    }

    public ChatBubble setTranslation(Vector3D translation) {
        this.translation = new Vector3f(translation.x, translation.y, translation.z);
        return this;
    }

    /*
     * This is the final scale before the out animation and after the in animation
     * which will stay the same if not modified
     */
    public Vector3D getScale() {
        return new Vector3D(this.scale.x, this.scale.y, this.scale.z);
    }

    /**
     * This is the final scale before the out animation and after the in animation
     * which will stay the same if not modified
     * @param scale
     * @return
     */
    public ChatBubble setScale(Vector3D scale) {
        this.finalScale = new Vector3D(scale.x, scale.y, scale.z);
        this.scale = new Vector3f(scale.x, scale.y, scale.z);
        return this;
    }

    /**
     * This is the current scale of the entity...
     * In the IN or OUT animation this scale will be different
     * @param scale
     * @return this object
     */
    public ChatBubble setCurrentScale(Vector3f scale) {
        this.scale = scale;
        return this;
    }

    public Component getTextAsComponent() {
        return this.text;
    }

    public String getText() {
        return ((TextComponent)this.text).content();
    }

    public String getTextWithoutColor() {
        return ChatColor.stripColor(getText());
    }

    public ChatBubble setText(String text) {
        this.text = Component.text(text);
        return this;
    }

    public ChatBubble setText(Component component) {
        this.text = component;
        return this;
    }

    public ChatBubble setMiniMessageText(String text) {
        this.text = Mini.message(text);
        return this;
    }
}


