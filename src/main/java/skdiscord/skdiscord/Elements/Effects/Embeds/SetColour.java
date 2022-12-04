package skdiscord.skdiscord.Elements.Effects.Embeds;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.Kleenean;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.Nullable;
import skdiscord.skdiscord.SkDiscord;

import java.awt.*;

public class SetColour extends Effect {
    static {
        Skript.registerEffect(SetColour.class, "Set colour of [embed] %string% to %string%");
    }
    private Expression<String> id;
    private Expression<String> colour;
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.id = (Expression<String>) expressions[0];
        this.colour = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    protected void execute(Event event) {
        // color is formatted as "r,g,b"
        try {
            EmbedBuilder builder = SkDiscord.embeds.get(id.getSingle(event));
            String[] rgb = colour.getSingle(event).split(",");
            int r = Integer.parseInt(rgb[0]);
            int g = Integer.parseInt(rgb[1]);
            int b = Integer.parseInt(rgb[2]);
            builder.setColor(new Color(r, g, b));
            SkDiscord.embeds.put(id.getSingle(event), builder);
        }
        catch (Exception e) {
            Bukkit.getLogger().warning("Error setting embed colour " + e);
        }
    }
}