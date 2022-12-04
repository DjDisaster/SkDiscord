package skdiscord.skdiscord;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public final class SkDiscord extends JavaPlugin {
    public static HashMap<String, JDA> bots = new HashMap<>();
    public static HashMap<String, EmbedBuilder> embeds = new HashMap<String, EmbedBuilder>();

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[SkDiscord] I have been summoned to your world!");
        Bukkit.getLogger().info("But do not worry, I wont destroy it as much as DISKY");
        Bukkit.getLogger().info("<3");
        SkriptAddon addon = Skript.registerAddon(this);
        try {

            addon.loadClasses("skdiscord.skdiscord", "Elements");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDisable() {
        Bukkit.getLogger().info("I have left this world...");
    }
}
