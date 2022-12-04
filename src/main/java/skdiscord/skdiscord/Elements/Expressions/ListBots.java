package skdiscord.skdiscord.Elements.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.Nullable;

public class ListBots extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ListBots.class, String.class, ExpressionType.SIMPLE, "[Discord] bots");
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "bungee servers";
    }

    @Override
    protected String[] get(Event e) {
        return skdiscord.skdiscord.SkDiscord.bots.keySet().toArray(new String[0]);
    }
}