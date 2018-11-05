/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  cn.nukkit.Player
 *  cn.nukkit.Server
 *  cn.nukkit.command.Command
 *  cn.nukkit.command.CommandSender
 *  cn.nukkit.utils.TextFormat
 */
package Broadcaster.Commands;

import Broadcaster.Broadcaster;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import java.util.Collection;
import java.util.Map;

public class SendMessage
extends Command {
    private Broadcaster plugin;

    public SendMessage(Broadcaster plugin) {
        super("sendmessage", "Send a message to the specified player (* for all players)", "/sendmessage <player> <message>");
        this.setAliases(new String[]{"sm", "smsg"});
        this.setPermission("broadcaster.sendmessage");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(TextFormat.colorize((String)"&cYou don't have permission to use this command!"));
        } else if (args.length > 1) {
            if (args[0].equals("*")) {
                if (sender instanceof CommandSender) {
                    for (Player onlineplayers : this.plugin.getServer().getOnlinePlayers().values()) {
                        onlineplayers.sendMessage(TextFormat.colorize((String)this.plugin.messagebyConsole(sender, this.plugin.getMessagefromArray(args))));
                    }
                } else if (sender instanceof Player) {
                    for (Player onlineplayers : this.plugin.getServer().getOnlinePlayers().values()) {
                        onlineplayers.sendMessage(TextFormat.colorize((String)this.plugin.messagebyPlayer((Player)sender, this.plugin.getMessagefromArray(args))));
                    }
                }
            } else if (this.plugin.getServer().getPlayerExact(args[0]) != null) {
                Player receiver = this.plugin.getServer().getPlayerExact(args[0]);
                if (sender instanceof CommandSender) {
                    receiver.sendMessage(TextFormat.colorize((String)this.plugin.messagebyConsole(sender, this.plugin.getMessagefromArray(args))));
                } else if (sender instanceof Player) {
                    receiver.sendMessage(TextFormat.colorize((String)this.plugin.messagebyPlayer((Player)sender, this.plugin.getMessagefromArray(args))));
                }
            } else {
                sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&cPlayer not found")));
            }
        } else {
            sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&cUsage: /sm <player> <message>")));
        }
        return true;
    }
}

