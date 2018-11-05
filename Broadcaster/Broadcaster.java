/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  cn.nukkit.Player
 *  cn.nukkit.Server
 *  cn.nukkit.command.Command
 *  cn.nukkit.command.CommandSender
 *  cn.nukkit.command.SimpleCommandMap
 *  cn.nukkit.plugin.PluginBase
 *  cn.nukkit.plugin.PluginLogger
 *  cn.nukkit.scheduler.ServerScheduler
 *  cn.nukkit.scheduler.Task
 *  cn.nukkit.scheduler.TaskHandler
 *  cn.nukkit.utils.Config
 *  cn.nukkit.utils.TextFormat
 */
package Broadcaster;

import Broadcaster.Commands.Commands;
import Broadcaster.Commands.SendMessage;
import Broadcaster.Commands.SendPopup;
import Broadcaster.Tasks.PopupTask;
import Broadcaster.Tasks.Task;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginLogger;
import cn.nukkit.scheduler.ServerScheduler;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Broadcaster
extends PluginBase {
    public static String PREFIX = "&9[&eBroadcaster&9] ";
    private TaskHandler task;
    private TaskHandler ptask;
    public Config config;

    public void onEnable() {
        this.saveDefaultConfig();
        this.config = this.getConfig();
        this.broadcastCommand();
        this.broadcastEnable();
        this.getLogger().info(TextFormat.colorize((String)"&aBroadcaster successfully enabled!"));
    }

    private void broadcastCommand() {
        this.getServer().getCommandMap().register("broadcaster", (Command)new Commands(this));
        this.getServer().getCommandMap().register("broadcaster", (Command)new SendMessage(this));
        this.getServer().getCommandMap().register("broadcaster", (Command)new SendPopup(this));
    }

    public void broadcastEnable() {
        int time = this.config.getInt("time") * 20;
        int ptime = this.config.getInt("popup-time") * 20;
        this.task = this.getServer().getScheduler().scheduleRepeatingTask((cn.nukkit.scheduler.Task)new Task(this), time);
        this.ptask = this.getServer().getScheduler().scheduleRepeatingTask((cn.nukkit.scheduler.Task)new PopupTask(this), ptime);
    }

    public void broadcastDisable() {
        this.task.cancel();
        this.ptask.cancel();
    }

    public String broadcast(String message) {
        message = message.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        message = message.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        message = message.replace("{PREFIX}", this.config.getString("prefix"));
        message = message.replace("{SUFFIX}", this.config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.config.getString("datetime-format"));
        message = message.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize((String)message);
    }

    public String messagebyPlayer(Player player, String message) {
        String format = this.config.getString("sendmessage-format");
        format = format.replace("{MESSAGE}", message);
        format = format.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        format = format.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        format = format.replace("{PREFIX}", this.config.getString("prefix"));
        format = format.replace("{SENDER}", player.getName());
        format = format.replace("{SUFFIX}", this.config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.config.getString("datetime-format"));
        format = format.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize((String)format);
    }

    public String messagebyConsole(CommandSender player, String message) {
        String format = this.config.getString("sendmessage-format");
        format = format.replace("{MESSAGE}", message);
        format = format.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        format = format.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        format = format.replace("{PREFIX}", this.config.getString("prefix"));
        format = format.replace("{SENDER}", player.getName());
        format = format.replace("{SUFFIX}", this.config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.config.getString("datetime-format"));
        format = format.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize((String)format);
    }

    public String broadcastPopup(String message) {
        message = message.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        message = message.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        message = message.replace("{PREFIX}", this.config.getString("prefix"));
        message = message.replace("{SUFFIX}", this.config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.config.getString("datetime-format"));
        message = message.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize((String)message);
    }

    public String popupbyPlayer(Player player, String message) {
        String format = this.config.getString("sendmessage-format");
        format = format.replace("{MESSAGE}", message);
        format = format.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        format = format.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        format = format.replace("{PREFIX}", this.config.getString("prefix"));
        format = format.replace("{SENDER}", player.getName());
        format = format.replace("{SUFFIX}", this.config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.config.getString("datetime-format"));
        format = format.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize((String)format);
    }

    public String popupbyConsole(CommandSender player, String message) {
        String format = this.config.getString("sendpopup-format");
        format = format.replace("{MESSAGE}", message);
        format = format.replace("{MAXPLAYERS}", String.valueOf(this.getServer().getMaxPlayers()));
        format = format.replace("{TOTALPLAYERS}", String.valueOf(this.getServer().getOnlinePlayers().size()));
        format = format.replace("{PREFIX}", this.config.getString("prefix"));
        format = format.replace("{SENDER}", player.getName());
        format = format.replace("{SUFFIX}", this.config.getString("suffix"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.config.getString("datetime-format"));
        format = format.replace("{TIME}", dateFormat.format(new Date()));
        return TextFormat.colorize((String)format);
    }

    public String getMessagefromArray(String[] array) {
        String msg = "";
        for (int i = 1; i < array.length; ++i) {
            msg = msg + array[i] + " ";
        }
        if (msg.length() > 0) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return msg;
    }
}

