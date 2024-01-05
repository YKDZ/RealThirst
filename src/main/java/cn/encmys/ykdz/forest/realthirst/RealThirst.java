package cn.encmys.ykdz.forest.realthirst;

import cn.encmys.ykdz.forest.realthirst.config.MainConfig;
import cn.encmys.ykdz.forest.realthirst.hook.MMOItemsHook;
import cn.encmys.ykdz.forest.realthirst.hook.PlaceholderAPIHook;
import cn.encmys.ykdz.forest.realthirst.listener.PlayerListener;
import cn.encmys.ykdz.forest.realthirst.listener.hook.MMOItemsListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RealThirst extends JavaPlugin {
    private static RealThirst plugin;

    @Override
    public void onEnable() {
        plugin = this;

        MainConfig.load();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), plugin);

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPIHook(plugin).register();
        }

        if(Bukkit.getPluginManager().getPlugin("MMOItems") != null) {
            MMOItemsHook.register();
            Bukkit.getPluginManager().registerEvents(new MMOItemsListener(), plugin);
        }
    }

    @Override
    public void onDisable() {

    }

    public static RealThirst getPlugin() {
        return plugin;
    }
}
