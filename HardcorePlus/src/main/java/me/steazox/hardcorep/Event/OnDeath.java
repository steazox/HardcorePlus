package me.steazox.hardcorep.Event;

import me.steazox.hardcorep.HardcoreP;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

            Date banExpiration = new Date(System.currentTimeMillis() + core.getConfig().getInt("ban.ban-time") * 60000);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String formattedTime = sdf.format(banExpiration);
            String banMessgae = ChatColor.RED + "You have been banned for 2 hours.\n"
                    + ChatColor.YELLOW + "Reason: " + ChatColor.WHITE + banReason + "\n"
                    + ChatColor.YELLOW + "Ban will expire at: " + ChatColor.WHITE + formattedTime + " UTC";
            BanList banList = Bukkit.getBanList(BanList.Type.NAME);

            banList.addBan(player.getName(), banMessgae, banExpiration, "Server");

            // Kick the player from the server with a stylized message
            String kickMessage = ChatColor.RED + "You have been banned for 2 hours.\n"
                    + ChatColor.YELLOW + "Reason: " + ChatColor.WHITE + banReason + "\n"
                    + ChatColor.YELLOW + "Ban will expire at: " + ChatColor.WHITE + formattedTime + " UTC";

            player.kickPlayer(kickMessage);
        }
    }
}
