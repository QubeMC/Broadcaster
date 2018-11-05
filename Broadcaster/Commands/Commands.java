/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  cn.nukkit.command.Command
 *  cn.nukkit.command.CommandSender
 *  cn.nukkit.plugin.PluginDescription
 *  cn.nukkit.utils.Config
 *  cn.nukkit.utils.TextFormat
 */
package Broadcaster.Commands;

import Broadcaster.Broadcaster;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import java.util.List;

public class Commands
extends Command {
    private Broadcaster plugin;

    public Commands(Broadcaster plugin) {
        super("broadcaster", "Broadcaster Commands.", "/broadcaster <on|off|reload|info>");
        this.setPermission("broadcaster");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(TextFormat.colorize((String)"&cYou don't have permission to use this command!"));
        } else if (args.length > 0) {
            switch (args[0]) {
                case "on": {
                    if (sender.hasPermission("broadcaster.on")) {
                        if (args.length > 1) {
                            if (args[1].equalsIgnoreCase("broadcast")) {
                                this.plugin.config.set("broadcast-enabled", (Object)true);
                                this.plugin.config.save();
                                sender.sendMessage(TextFormat.colorize((String)"&aBroadcast successfully enabled!"));
                                break;
                            }
                            if (args[1].equalsIgnoreCase("popup")) {
                                this.plugin.config.set("popup-broadcast-enabled", (Object)true);
                                this.plugin.config.save();
                                sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&aPopup broadcast successfully enabled!")));
                                break;
                            }
                            sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&cUsage: /broadcaster on <broadcast|popup>")));
                            break;
                        }
                        if (!this.plugin.config.getBoolean("broadcast-enabled") || !this.plugin.config.getBoolean("popup-broadcast-enabled")) {
                            this.plugin.config.set("broadcast-enabled", (Object)true);
                            this.plugin.config.set("popup-broadcast-enabled", (Object)true);
                            this.plugin.config.save();
                            sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&aBroadcaster successfully enabled!")));
                            break;
                        }
                        sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&cBroadcast already enabled!")));
                        break;
                    }
                    sender.sendMessage(TextFormat.colorize((String)"&cYou don't have permission to use this command!"));
                    break;
                }
                case "off": {
                    if (sender.hasPermission("broadcaster.off")) {
                        if (args.length > 1) {
                            if (args[1].equalsIgnoreCase("broadcast")) {
                                this.plugin.config.set("broadcast-enabled", (Object)false);
                                this.plugin.config.save();
                                sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&aBroadcast successfully disabled!")));
                                break;
                            }
                            if (args[1].equalsIgnoreCase("popup")) {
                                this.plugin.config.set("popup-broadcast-enabled", (Object)false);
                                this.plugin.config.save();
                                sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&aPopup broadcast successfully disabled!")));
                                break;
                            }
                            sender.sendMessage(TextFormat.colorize((String)"&cUsage: /broadcaster off <broadcast|popup>"));
                            break;
                        }
                        if (this.plugin.config.getBoolean("broadcast-enabled") || this.plugin.config.getBoolean("popup-broadcast-enabled")) {
                            this.plugin.config.set("broadcast-enabled", (Object)false);
                            this.plugin.config.set("popup-broadcast-enabled", (Object)false);
                            this.plugin.config.save();
                            sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&aBroadcaster successfully disabled!")));
                            break;
                        }
                        sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&cBroadcast already disabled!")));
                        break;
                    }
                    sender.sendMessage(TextFormat.colorize((String)"&cYou don't have permission to use this command!"));
                    break;
                }
                case "reload": {
                    if (sender.hasPermission("broadcaster.reload")) {
                        this.plugin.reloadConfig();
                        this.plugin.broadcastDisable();
                        this.plugin.broadcastEnable();
                        sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&aConfiguration Reloaded.")));
                        break;
                    }
                    sender.sendMessage(TextFormat.colorize((String)"&cYou don't have permission to use this command!"));
                    break;
                }
                case "info": {
                    if (sender.hasPermission("broadcaster.info")) {
                        sender.sendMessage(TextFormat.colorize((String)("&9[&eInfo&9] &a" + this.plugin.getDescription().getName() + " &bv" + this.plugin.getDescription().getVersion())));
                        sender.sendMessage(TextFormat.colorize((String)("&eAuthor: &c" + this.plugin.getDescription().getAuthors())));
                        sender.sendMessage(TextFormat.colorize((String)("&dWebSite: &7" + this.plugin.getDescription().getWebsite())));
                        sender.sendMessage(TextFormat.colorize((String)"&9VK: &bhttps://vk.com/goganselot"));
                        break;
                    }
                    sender.sendMessage(TextFormat.colorize((String)"&cYou don't have permission to use this command!"));
                    break;
                }
                default: {
                    sender.sendMessage(TextFormat.colorize((String)(Broadcaster.PREFIX + "&cSubcommand &b" + args[0] + "&c not found. Use &e/broadcaster &cto show available commands")));
                    break;
                }
            }
        } else {
            sender.sendMessage(TextFormat.colorize((String)"&7- &aAvailable Commands &7-"));
            sender.sendMessage(TextFormat.colorize((String)"&e/broadcaster on <broadcast|popup> &7- &aEnable messages from broadcaster"));
            sender.sendMessage(TextFormat.colorize((String)"&e/broadcaster off <broadcast|popup> &7- &aDisable messages from broadcaster"));
            sender.sendMessage(TextFormat.colorize((String)"&e/broadcaster info &7- &aShow info about this plugin"));
            sender.sendMessage(TextFormat.colorize((String)"&e/broadcaster reload &7- &aReload the config"));
            sender.sendMessage(TextFormat.colorize((String)"&e/sendmessage &7- &aSend message to the specified player (* for all players)"));
            sender.sendMessage(TextFormat.colorize((String)"&e/sendpopup &7- &aSend popup to the specified player (* for all players)"));
        }
        return true;
    }
}

