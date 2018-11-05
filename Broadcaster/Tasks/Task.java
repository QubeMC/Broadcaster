/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  cn.nukkit.Server
 *  cn.nukkit.plugin.Plugin
 *  cn.nukkit.scheduler.PluginTask
 *  cn.nukkit.utils.Config
 */
package Broadcaster.Tasks;

import Broadcaster.Broadcaster;
import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.PluginTask;
import cn.nukkit.utils.Config;
import java.util.ArrayList;
import java.util.List;

public class Task
extends PluginTask<Broadcaster> {
    private Broadcaster plugin;
    private int length;

    public Task(Broadcaster plugin) {
        super((Plugin)plugin);
        this.plugin = plugin;
        this.length = -1;
    }

    public void onRun(int currentTick) {
        if (this.plugin.getConfig().getBoolean("broadcast-enabled", true)) {
            ArrayList messages;
            ++this.length;
            try {
                messages = this.plugin.getConfig().getList("messages");
            }
            catch (Exception e) {
                messages = new ArrayList();
            }
            String message = (String)messages.get(this.length);
            if (this.length == messages.size() - 1) {
                this.length = -1;
            }
            Server.getInstance().broadcastMessage(this.plugin.broadcast(message));
        }
    }
}

