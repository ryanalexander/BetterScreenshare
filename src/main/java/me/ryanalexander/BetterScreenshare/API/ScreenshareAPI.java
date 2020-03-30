package me.ryanalexander.BetterScreenshare.API;

import me.ryanalexander.BetterScreenshare.Utils.PlayerScreenshare;
import me.ryanalexander.BetterScreenshare.Variables.ScreenshareReason;
import org.bukkit.entity.Player;

public class ScreenshareAPI {

    public void doScreenshare(Player player, ScreenshareReason reason){
        new PlayerScreenshare(player,null,reason);
    }

    public boolean isScreensharing(Player player){
        return PlayerScreenshare.targets.containsKey(player);
    }

    public Player[] getScreensharing() {
        return PlayerScreenshare.targets.keySet().toArray(new Player[]{});
    }

}
