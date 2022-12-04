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

public class SetField extends Effect {
    static {
        Skript.registerEffect(SetField.class, "Set field %string% to %string% in embed %string%");
    }

    private Expression<String> field;
    private Expression<String> fieldvalue;
    private Expression<String> id;
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.field = (Expression<String>) expressions[0];
        this.fieldvalue = (Expression<String>) expressions[1];
        this.id = (Expression<String>) expressions[2];
        return true;
    }

    @Override
    protected void execute(Event event) {
        try {
            EmbedBuilder builder = SkDiscord.embeds.get(id.getSingle(event));
            builder.addField(field.getSingle(event), fieldvalue.getSingle(event), false);
            SkDiscord.embeds.put(id.getSingle(event), builder);
        }
            catch (Exception e) {
            Bukkit.getLogger().warning("Error setting embed field " + e);
        }
    }

}