package skdiscord.skdiscord.Elements.Effects.Embeds;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.Nullable;
import skdiscord.skdiscord.SkDiscord;

public class EmbedTitle extends Effect {
    static {
        Skript.registerEffect(EmbedTitle.class, "Set title of %string% to %string%");
    }
    private Expression<String> id;
    private Expression<String> name;

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.id = (Expression<String>) expressions[0];
        this.name = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    protected void execute(Event event) {
        try {
            EmbedBuilder builder = SkDiscord.embeds.get(id.getSingle(event));
            builder.addField("yooooo", "this is a test", false);
            builder.setTitle(name.getSingle(event));
            SkDiscord.embeds.put(id.getSingle(event), builder);
        }
            catch (Exception e) {
            Bukkit.getLogger().warning("Error setting embed title " + e);
        }
    }

}