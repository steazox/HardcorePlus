package me.steazox.hardcorep.Event;

import me.steazox.hardcorep.HardcoreP;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OnDeath implements Listener {

    private HardcoreP core;

    public OnDeath(HardcoreP core) {
        this.core = core;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        // Get the player who died
        Player player = e.getEntity();

        // Drop the head of the killed player
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
        skullMeta.setOwningPlayer(player);
        playerHead.setItemMeta(skullMeta);

        player.getWorld().dropItemNaturally(player.getLocation(), playerHead);

        // Check if the player is online
        if (player.isOnline()) {

            // Ban the player
            String banReason = "Died in HardcorePlus";

            long expirationTimeMillis = getExpirationTimeInMillis();
            Date banExpiration = new Date(expirationTimeMillis);

            String banMessage = core.getConfig().getString("ban.ban-message");
            banMessage = banMessage.replace("{expiration}", getFormattedExpirationTime());
            BanList banList = Bukkit.getBanList(BanList.Type.NAME);

            banList.addBan(player.getName(), banMessage, banExpiration, "Server");

            player.kickPlayer(banMessage);
        }


    }

    private String getFormattedExpirationTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date(System.currentTimeMillis() + core.getConfig().getInt("ban.ban-time") * 60000)) + " UTC";
    }

    private long getExpirationTimeInMillis() {
        return System.currentTimeMillis() + core.getConfig().getInt("ban.ban-time") * 60000;
    }

}
