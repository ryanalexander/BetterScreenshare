package me.ryanalexander.BetterScreenshare.Commands;

import com.google.common.base.Enums;
import me.ryanalexander.BetterScreenshare.Utils.PlayerScreenshare;
import me.ryanalexander.BetterScreenshare.Variables.ScreenshareReason;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ScreenshareCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(args.length==0||Bukkit.getPlayer(args[0])==null||!Bukkit.getPlayer(args[0]).isOnline())
            return false;

        Player target = Bukkit.getPlayer(args[0]);

        if(PlayerScreenshare.targets.containsKey(target)){
            PlayerScreenshare.targets.get(target).cancel();
        }else {
            ScreenshareReason reason = ScreenshareReason.OTHER;

            if (args.length > 1)
                reason = Enums.getIfPresent(ScreenshareReason.class, args[1]).or(ScreenshareReason.OTHER);

            new PlayerScreenshare(target, (sender instanceof Player ? (Player) sender : null), reason);
        }
        return true;
    }
}
