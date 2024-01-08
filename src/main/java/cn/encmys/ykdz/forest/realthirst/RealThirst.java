package cn.encmys.ykdz.forest.realthirst;

import cn.encmys.ykdz.forest.realthirst.config.MainConfig;
import cn.encmys.ykdz.forest.realthirst.hook.MMOItemsHook;
import cn.encmys.ykdz.forest.realthirst.hook.PlaceholderAPIHook;
import cn.encmys.ykdz.forest.realthirst.listener.PlayerListener;
import cn.encmys.ykdz.forest.realthirst.listener.hook.MMOItemsListener;
import cn.encmys.ykdz.forest.realthirst.schedule.ThirstSchedule;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RealThirst extends JavaPlugin {
    private static RealThirst plugin;
    private static ProtocolManager protocolManager;
    private static ThirstSchedule thirstSchedule;

    public static ThirstSchedule getThirstSchedule() {
        return thirstSchedule;
    }

    @Override
    public void onLoad() {
        plugin = this;

        MainConfig.load();

        if(Bukkit.getPluginManager().getPlugin("MMOItems") != null) {
            MMOItemsHook.register();
            plugin.getLogger().info("Hooked onto MMOItems");
        }
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), plugin);

        protocolManager = ProtocolLibrary.getProtocolManager();

        thirstSchedule = new ThirstSchedule(plugin);

        if(Bukkit.getPluginManager().getPlugin("MMOItems") != null) {
            Bukkit.getPluginManager().registerEvents(new MMOItemsListener(), plugin);
        }

        if(Bukkit.getPluginManager().getPlugin("RealisticSeasons") != null) {
            plugin.getLogger().info("Hooked onto RealisticSeasons");
        }

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPIHook(plugin).register();
            plugin.getLogger().info("Hooked onto PlaceholderAPI");
        }
    }

    @Override
    public void onDisable() {

    }

    public static RealThirst getPlugin() {
        return plugin;
    }

    public static boolean isHookedPluginEnabled(String plugin) {
        return Bukkit.getPluginManager().isPluginEnabled(plugin);
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
