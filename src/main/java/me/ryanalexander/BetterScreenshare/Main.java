package me.ryanalexander.BetterScreenshare;

import me.ryanalexander.BetterScreenshare.Commands.ScreenshareCommand;
import me.ryanalexander.BetterScreenshare.Events.PlayerEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        PluginManager pm = Bukkit.getPluginManager();

        // Load config file
        saveDefaultConfig();

        // Register Events
        pm.registerEvents(new PlayerEvents(),this);


        // Register Commands
        getCommand("screenshare").setExecutor(new ScreenshareCommand());

    }

}
