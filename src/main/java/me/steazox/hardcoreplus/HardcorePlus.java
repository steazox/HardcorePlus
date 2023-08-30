package me.steazox.hardcoreplus;

import me.steazox.hardcoreplus.Event.OnDeath;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HardcorePlus extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new OnDeath(), this);
    }
}
