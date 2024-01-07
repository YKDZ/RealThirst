package cn.encmys.ykdz.forest.realthirst.utils;

import cn.encmys.ykdz.forest.realthirst.RealThirst;
import cn.encmys.ykdz.forest.realthirst.config.MainConfig;
import org.bukkit.entity.Player;

public class DebugUtils {

    public static void sendConsoleMessage(String message) {
        if(MainConfig.debug) {
            RealThirst.getPlugin().getLogger().info(message);
        }
    }

    public static void sendPlayerMessage(Player player, String message) {
        if(MainConfig.debug) {
            player.sendMessage(message);
        }
    }
}
