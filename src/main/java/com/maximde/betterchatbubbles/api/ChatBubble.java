package com.maximde.betterchatbubbles.api;

import com.github.retrooper.packetevents.protocol.particle.type.ParticleType;
import com.github.retrooper.packetevents.protocol.particle.type.ParticleTypes;
import com.github.retrooper.packetevents.util.Vector3f;
import com.maximde.betterchatbubbles.api.utils.Mini;
import com.maximde.betterchatbubbles.api.utils.Vector3D;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

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
    protected int animationDurationUp = 5;

    /**
     * The duration for the animation in ticks
     */
    @Setter @Getter @Accessors(chain = true)
    protected int animationDurationIn = 5;

    @Setter @Getter @Accessors(chain = true)
    protected int animationDurationOut = 5;

    @Setter @Getter @Accessors(chain = true)
    protected int duration = 5;

    @Setter @Getter @Accessors(chain = true)
    protected AnimationType animationTypeIn = AnimationType.SCALE;

    @Setter @Getter @Accessors(chain = true)
    protected AnimationType animationTypeOut = AnimationType.SCALE;

    @Setter @Getter @Accessors(chain = true)
    protected Sound animationInSound = Sound.UI_TOAST_IN;

    @Setter @Getter @Accessors(chain = true)
    protected Sound animationOutSound = Sound.UI_TOAST_OUT;

    @Setter @Getter @Accessors(chain = true)
    protected boolean soundIn = true;

    @Setter @Getter @Accessors(chain = true)
    protected boolean soundOut = true;

    @Getter
    protected ParticleType animationParticleType = ParticleTypes.END_ROD;
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
        this.text = Component.text(replaceFontImages(text));
        return this;
    }

    public ChatBubble setText(Component component) {
        this.text = component;
        return this;
    }

    public ChatBubble setMiniMessageText(String text) {
        this.text = Mini.message(replaceFontImages(text));
        return this;
    }

    private String replaceFontImages(String s) {
        AtomicReference<String> news = new AtomicReference<>(s);
        Optional<BubbleAPI> bubble = BubbleAPI.getBubbleAPI();
        bubble.ifPresent(bubbleAPI -> news.set(bubbleAPI.getReplaceText().replace(news.get())));
        return news.get();
    }

    /**
     * This is the correct way to get the location without any possible errors!
     */
    public Location getLocation() {
        return isUseTargetEntityID() ? getTargetEntityLocation() : getTarget().getLocation();
    }

    public ChatBubble setAnimationParticleType(String particleType) {
        try {
            Field field = ParticleTypes.class.getField(particleType.toUpperCase());
            Object value = field.get(null);
            if (value instanceof ParticleType) {
                this.animationParticleType = (ParticleType) value;
            } else {
                Bukkit.getLogger().log(Level.SEVERE, "Could not apply particle type " + particleType + "! Using default type now.");
                this.animationParticleType = ParticleTypes.END_ROD;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not find particle type " + particleType + "! Using default type now.");
            this.animationParticleType = ParticleTypes.END_ROD;
        }
        return this;
    }

    public String getAnimationParticleTypeString() {
        return this.animationParticleType.getName().getKey().toUpperCase();
    }

}


