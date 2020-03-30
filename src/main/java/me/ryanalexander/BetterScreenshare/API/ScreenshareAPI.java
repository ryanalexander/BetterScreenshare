package me.ryanalexander.BetterScreenshare.API;

import me.ryanalexander.BetterScreenshare.Utils.PlayerScreenshare;
import me.ryanalexander.BetterScreenshare.Variables.ScreenshareReason;
import org.bukkit.entity.Player;

public class ScreenshareAPI {

    /**
     * Pragmatically enter a player into screenshare mode.
     * @param player Bukkit player object
     * @param reason ScreenshareReason object
     */
    public void doScreenshare(Player player, ScreenshareReason reason){
        new PlayerScreenshare(player,null,reason);
    }

    /**
     * Check if a player is currently being screenshared
     * @param player Bukkit player objecvt
     * @return Player screenshare state
     */
    public boolean isScreensharing(Player player){
        return PlayerScreenshare.targets.containsKey(player);
    }

    /**
     * Get a list of players screenshared
     * @return List of players currently being screenshared
     */
    public Player[] getScreensharing() {
        return PlayerScreenshare.targets.keySet().toArray(new Player[]{});
    }

}
