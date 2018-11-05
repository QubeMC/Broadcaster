/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  cn.nukkit.Player
 *  cn.nukkit.Server
 *  cn.nukkit.plugin.Plugin
 *  cn.nukkit.scheduler.PluginTask
 *  cn.nukkit.scheduler.ServerScheduler
 */
package Broadcaster.Tasks;

import Broadcaster.Broadcaster;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.PluginTask;
import cn.nukkit.scheduler.ServerScheduler;
import java.util.Collection;
import java.util.Map;

public class PopupDurationTask
extends PluginTask<Broadcaster> {
    private Broadcaster plugin;
    private Player player;
    private String message;
    private int duration;
    private int current;

    public PopupDurationTask(Broadcaster plugin, String message, Player player, int duration) {
        super((Plugin)plugin);
        this.plugin = plugin;
        this.player = player;
        this.message = message;
        this.duration = duration;
        this.current = 0;
    }

    public void onRun(int currentTick) {
        if (this.current <= this.duration) {
            if (this.player != null) {
                this.message = this.message.replace("{PLAYER}", this.player.getName());
                this.player.sendPopup(this.plugin.broadcastPopup(this.message));
            } else {
                for (Player player : this.plugin.getServer().getOnlinePlayers().values()) {
                    this.message = this.message.replace("{PLAYER}", "*");
                    player.sendPopup(this.plugin.broadcastPopup(this.message));
                }
            }
        } else {
            this.plugin.getServer().getScheduler().cancelTask(this.getTaskId());
        }
        ++this.current;
    }
}

