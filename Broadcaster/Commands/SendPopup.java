/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  cn.nukkit.Player
 *  cn.nukkit.Server
 *  cn.nukkit.command.Command
 *  cn.nukkit.command.CommandSender
 *  cn.nukkit.scheduler.ServerScheduler
 *  cn.nukkit.scheduler.Task
 *  cn.nukkit.scheduler.TaskHandler
 *  cn.nukkit.utils.Config
 *  cn.nukkit.utils.TextFormat
 */
package Broadcaster.Commands;

import Broadcaster.Broadcaster;
import Broadcaster.Tasks.PopupDurationTask;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.scheduler.ServerScheduler;
import cn.nukkit.scheduler.Task;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class SendPopup
extends Command {
    private Broadcaster plugin;

    public SendPopup(Broadcaster plugin) {
        super("sendpopup", "Send a popup to the specified player (* for all players)", "/sendpopup <player> <message>");
        this.setAliases(new String[]{"sp", "spop"});
        this.setPermission("broadcaster.sendpopup");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(TextFormat.colorize((String)"&cYou don't have permission to use this command!"));
        } else if (args.length > 1) {
            if (args[0].equals("*")) {
                if (sender instanceof CommandSender) {
                    this.plugin.getServer().getScheduler().scheduleRepeatingTask((Task)new PopupDurationTask(this.plugin, this.plugin.popupbyConsole(sender, this.plugin.getMessagefromArray(args)), null, this.plugin.getConfig().getInt("popup-duration")), 10);
                } else if (sender instanceof Player) {
                    this.plugin.getServer().getScheduler().scheduleRepeatingTask((Task)new PopupDurationTask(this.plugin, this.plugin.popupbyPlayer((Player)sender, this.plugin.getMessagefromArray(args)), null, this.plugin.getConfig().getInt("popup-duration")), 10);
                }
            } else if (this.plugin.getServer().getPlayerExact(args[0]) != null) {
                Player receiver = this.plugin.getServer().getPlayerExact(args[0]);
                if (sender instanceof CommandSender) {
                    this.plugin.getServer().getScheduler().scheduleRepeatingTask((Task)new PopupDurationTask(this.plugin, this.plugin.popupbyConsole(sender, this.plugin.getMessagefromArray(args)), receiver, this.plugin.getConfig().getInt("popup-duration")), 10);
                } else if (sender instanceof Player) {
                    this.plugin.getServer().getScheduler().scheduleRepeatingTask((Task)new PopupDurationTask(this.plugin, this.plugin.popupbyPlayer((Player)sender, this.plugin.getMessagefromArray(args)), receiver, this.plugin.getConfig().getInt("popup-duration")), 10);
                }
            } else {
                sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&cPlayer not found")));
            }
        } else {
            sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&cUsage: /sp <player> <message>")));
        }
        return true;
    }
}

