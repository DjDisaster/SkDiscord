package skdiscord.skdiscord.Elements.Effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.Nullable;

public class Login extends Effect {
    static {
        Skript.registerEffect(Login.class, "Login to %string% named %string%");
    }

    private Expression<String> token;
    private Expression<String> name;
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.token = (Expression<String>) expressions[0];
        this.name = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    protected void execute(Event event) {
        JDA jda = JDABuilder.createDefault(token.getSingle(event)).build();
        skdiscord.skdiscord.SkDiscord.bots.put(name.getSingle(event), jda);
        // get all players with permission skdiscord.login and send them a message
        for (org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("skdiscord.login")) {
                player.sendMessage("[skdiscord.login] Logged in to " + name.getSingle(event));
            }
        }

    }

}