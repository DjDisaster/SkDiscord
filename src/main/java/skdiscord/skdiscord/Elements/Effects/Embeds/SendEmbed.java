package skdiscord.skdiscord.Elements.Effects.Embeds;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.Nullable;
import skdiscord.skdiscord.SkDiscord;

public class SendEmbed extends Effect {
    static {
        Skript.registerEffect(SendEmbed.class, "Send embed [with id] %string% to [channel with id] %string% with [bot] %string%");
    }

    private Expression<String> id;
    private Expression<String> channel;
    private Expression<String> bot;
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.id = (Expression<String>) expressions[0];
        this.channel = (Expression<String>) expressions[1];
        this.bot = (Expression<String>) expressions[2];
        return true;
    }

    @Override
    protected void execute(Event event) {
        // get imbed from hashmap with id and send it to channel with id
        try {
            JDA jda = SkDiscord.bots.get(bot.getSingle(event));
            //.await()
            EmbedBuilder builder = SkDiscord.embeds.get(id.getSingle(event));
            TextChannel channel = jda.getTextChannelById(this.channel.getSingle(event));
            channel.sendMessage("").setEmbeds(builder.build()).queue();

        }
        catch (Exception e) {
            Bukkit.getLogger().warning("Error sending message to discord " + e);
            Bukkit.getLogger().warning("This usually means the bot is not logged in or the channel is not found");
            Bukkit.getLogger().warning("It can also mean that the embed is invalid!");
        }
    }
}