/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  cn.nukkit.Player
 *  cn.nukkit.Server
 *  cn.nukkit.plugin.Plugin
 *  cn.nukkit.scheduler.PluginTask
 *  cn.nukkit.scheduler.ServerScheduler
 *  cn.nukkit.scheduler.Task
 *  cn.nukkit.scheduler.TaskHandler
 *  cn.nukkit.utils.Config
 */
package Broadcaster.Tasks;

import Broadcaster.Broadcaster;
import Broadcaster.Tasks.PopupDurationTask;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.PluginTask;
import cn.nukkit.scheduler.ServerScheduler;
import cn.nukkit.scheduler.Task;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.Config;
import java.util.ArrayList;
import java.util.List;

public class PopupTask
extends PluginTask<Broadcaster> {
    private Broadcaster plugin;
    private int length;

    public PopupTask(Broadcaster plugin) {
        super((Plugin)plugin);
        this.plugin = plugin;
        this.length = -1;
    }

    public void onRun(int currentTick) {
        if (this.plugin.getConfig().getBoolean("popup-broadcast-enabled", true)) {
            ArrayList popups;
            ++this.length;
            try {
                popups = this.plugin.getConfig().getList("popups");
            }
            catch (Exception e) {
                popups = new ArrayList();
            }
            String popup = (String)popups.get(this.length);
            if (this.length == popups.size() - 1) {
                this.length = -1;
            }
            this.plugin.getServer().getScheduler().scheduleRepeatingTask((Task)new PopupDurationTask(this.plugin, this.plugin.broadcast(popup), null, this.plugin.getConfig().getInt("popup-duration")), 10);
        }
    }
}

