package me.ryanalexander.BetterScreenshare.Utils;


import me.ryanalexander.BetterScreenshare.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class JavaUtils {

    public static boolean isPlural(int i){
        return i > 1 || i==0;
    }

    /**
     * Apply placeholders to message
     * @param screenshare Screenshare object
     * @param message Message to be formatted
     * @return Formatted message
     */
    public static String doApplyTranslations(PlayerScreenshare screenshare, String message){
        return message
                .replaceAll("(%player%)",screenshare.getTarget().getName())
                .replaceAll("(%reason%)",(screenshare.getReason()==null?"Suspected Cheating":screenshare.getReason().toString()))
                .replaceAll("(%staff)",(screenshare.getStaff()==null?"A member of staff":screenshare.getStaff().getName()));
    }

    /**
     * Apply placeholders to list of messages
     * @param screenshare Screenshare object
     * @param message Messages to be formatted
     * @return Formatted messages
     */
    public static String[] doApplyTranslations(PlayerScreenshare screenshare, String[] message){
        List<String> reply = new ArrayList<>();
        for(String line : message)
            reply.add(line
                .replaceAll("(%player%)",screenshare.getTarget().getName())
                .replaceAll("(%reason%)",(screenshare.getReason()==null?"Suspected Cheating":screenshare.getReason().toString()))
                .replaceAll("(%staff)",(screenshare.getStaff()==null?"A member of staff":screenshare.getStaff().getName())));
        return reply.toArray(new String[]{});
    }

    /**
     * Apply color codes to message
     * @param s Original message
     * @return Alt color char message
     */
    public static String format(String s){
        return ChatColor.translateAlternateColorCodes('&',s);
    }

    /**
     * Apply placeholders to message and apply color codes
     * @param s Message to be formatted
     * @param screenshare Screenshare object
     * @return Formatted message
     */
    public static String format(String s, PlayerScreenshare screenshare){
        return ChatColor.translateAlternateColorCodes('&',doApplyTranslations(screenshare,s));
    }

    /**
     * Apply color codes to list of messages
     * @param s List of messages
     * @return Alt color char messages
     */
    public static String[] format(String[] s){
        List<String> reply = new ArrayList<>();
        for(String line : s)
            reply.add(format(line));
        return reply.toArray(new String[]{});
    }

    /**
     * Apply placeholders to list of messages and apply color codes
     * @param s Messages to be formatted
     * @param screenshare Screenshare object
     * @return Formatted messages
     */
    public static String[] format(String[] s, PlayerScreenshare screenshare){
        List<String> reply = new ArrayList<>();
        for(String line : doApplyTranslations(screenshare,s))
            reply.add(format(line));
        return reply.toArray(new String[]{});
    }

    public static void broadcastStage(String stage, PlayerScreenshare screenshare){

        Player target = screenshare.getTarget();
        Player staff = screenshare.getStaff();

        // Fetch config
        FileConfiguration config = Main.getPlugin(Main.class).getConfig();

        // Message all required players
        if(target.isOnline()&&config.contains("lang."+stage+".player"))
            target.sendMessage(JavaUtils.format(config.getStringList("lang."+stage+".player").toArray(new String[]{}),screenshare));

        if(config.contains("lang."+stage+".broadcast"))
            for(Player player : Bukkit.getOnlinePlayers())
                if(player!=target)
                player.sendMessage(JavaUtils.format(config.getStringList("lang."+stage+".broadcast").toArray(new String[]{}),screenshare));

        if(staff!=null&&staff.isOnline()&&config.contains("lang."+stage+".staff"))
            staff.sendMessage(JavaUtils.format(config.getStringList("lang."+stage+".staff").toArray(new String[]{}),screenshare));
    }
}