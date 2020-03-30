package me.ryanalexander.BetterScreenshare.Utils;

import me.ryanalexander.BetterScreenshare.Main;
import me.ryanalexander.BetterScreenshare.Variables.ScreenshareReason;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerScreenshare {

    public static HashMap<Player, PlayerScreenshare> targets = new HashMap<>();


    Player target;
    Player staff;
    ScreenshareReason reason;

    public PlayerScreenshare(Player target, Player staff, ScreenshareReason reason){
        this.target=target;
        this.staff=staff;
        this.reason=reason;

        // Prevent player from moving.
        target.setAllowFlight(true);
        target.setWalkSpeed(0);
        target.setFlySpeed(0);

        targets.put(target,this);

        JavaUtils.broadcastStage("initiate",this);
    }

    public Player getTarget() {
        return target;
    }

    public Player getStaff() {
        return staff;
    }

    public ScreenshareReason getReason() {
        return reason;
    }

    public void cancel() {
        target.setAllowFlight(false);
        target.setWalkSpeed(0.01F);
        target.setFlySpeed(0.01F);
        targets.remove(target);

        JavaUtils.broadcastStage("done",this);
    }
}
