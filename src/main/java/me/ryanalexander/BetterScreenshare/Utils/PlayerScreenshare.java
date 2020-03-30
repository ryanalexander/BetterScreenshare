package me.ryanalexander.BetterScreenshare.Utils;

import me.ryanalexander.BetterScreenshare.Variables.ScreenshareReason;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class PlayerScreenshare {

    public static HashMap<Player, PlayerScreenshare> targets = new HashMap<>();


    Player target;
    Player staff;
    ScreenshareReason reason;

    /**
     * Put player in screenshare mode
     * @param target Bukkit player object of player to be Screenshared
     * @param staff Bukkit player object of player administering the Screenshare
     * @param reason ScreenshareReason object of why the player is being screenshared
     */
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

    /**
     * Get player being screenshared
     * @return Bukkit player object
     */
    public Player getTarget() {
        return target;
    }

    /**
     * Get player administering screenshare
     * @return Bukkit player object
     */
    public Player getStaff() {
        return staff;
    }

    /**
     * Get reason for screenshare
     * @return ScreenshareReason object
     */
    public ScreenshareReason getReason() {
        return reason;
    }

    /**
     * Cancel/end screenshare in progress.
     */
    public void cancel() {
        target.setAllowFlight(false);
        target.setWalkSpeed(0.01F);
        target.setFlySpeed(0.01F);
        targets.remove(target);

        JavaUtils.broadcastStage("done",this);
    }
}
