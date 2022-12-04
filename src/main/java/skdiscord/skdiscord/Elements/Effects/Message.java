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

public class Message extends Effect {
    static {
        Skript.registerEffect(Message.class, "Send %string% to [channel] %string% with [bot] %string%");
    }

    private Expression<String> message;
    private Expression<String> channel;
    private Expression<String> bot;
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.message = (Expression<String>) expressions[0];
        this.channel = (Expression<String>) expressions[1];
        this.bot = (Expression<String>) expressions[2];
        return true;
    }

    @Override
    protected void execute(Event event) {
        try {
            JDA jda = skdiscord.skdiscord.SkDiscord.bots.get(bot.getSingle(event));
            jda.getTextChannelById(channel.getSingle(event)).sendMessage(message.getSingle(event)).queue();

        }
            catch (Exception e) {
            Bukkit.getLogger().warning("Error sending message to discord " + e);
            Bukkit.getLogger().warning("This usually means the bot is not logged in or the channel is not found");
        }
    }

}