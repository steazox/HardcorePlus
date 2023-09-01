package me.steazox.hardcorep;

import me.steazox.hardcorep.Event.OnDeath;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HardcoreP extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new OnDeath(this), this);
        getLogger().info(getConfig().getInt("ban.ban-time") + " minutes of ban");
        getLogger().info(getConfig().getString("ban.ban-message"));
    }
}