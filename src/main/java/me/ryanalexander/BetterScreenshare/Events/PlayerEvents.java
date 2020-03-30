package me.ryanalexander.BetterScreenshare.Events;

import me.ryanalexander.BetterScreenshare.Main;
import me.ryanalexander.BetterScreenshare.Utils.JavaUtils;
import me.ryanalexander.BetterScreenshare.Utils.PlayerScreenshare;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

public class PlayerEvents implements Listener {

    @EventHandler
    public void AsyncChat(AsyncPlayerChatEvent e){
        e.getRecipients().removeAll(PlayerScreenshare.targets.keySet());
    }

    @EventHandler
    public void CommandEvent(PlayerCommandPreprocessEvent e){
        if(PlayerScreenshare.targets.containsKey(e.getPlayer())) {
            e.getPlayer().sendMessage(JavaUtils.format("&cYou may not execute commands while being screenshared."));
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerLeave(PlayerQuitEvent e){
        if(PlayerScreenshare.targets.containsKey(e.getPlayer())) {

            // Broadcast disconnect messages
            JavaUtils.broadcastStage("disconnect",PlayerScreenshare.targets.get(e.getPlayer()));

            // Execute disconnect commands
            FileConfiguration config = Main.getPlugin(Main.class).getConfig();
            for(String command : config.getStringList("commands.screenshare_disconnect"))
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),JavaUtils.doApplyTranslations(PlayerScreenshare.targets.get(e.getPlayer()),command));
        }
    }

    @EventHandler
    public void PlayerMove(PlayerMoveEvent e){
        // Allow player to look around, but deny any other movement.
        if(PlayerScreenshare.targets.containsKey(e.getPlayer())) {
            if(
                    e.getTo().getX()!=e.getFrom().getX()||
                    e.getTo().getY()!=e.getFrom().getY()||
                    e.getTo().getZ()!=e.getFrom().getZ()
            )
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerDamagePlayer(EntityDamageByEntityEvent e){
        // Prevent the player damaging other players, or other players damaging player.
        if(
                ((e.getDamager() instanceof Player)&&
                (PlayerScreenshare.targets.containsKey((Player)e.getDamager())||
                e.getEntity() instanceof Player && PlayerScreenshare.targets.containsKey((Player)e.getEntity()))))
            e.setCancelled(true);
    }
}
