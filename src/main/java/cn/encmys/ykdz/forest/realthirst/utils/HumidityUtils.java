package cn.encmys.ykdz.forest.realthirst.utils;

import org.bukkit.Location;

public class HumidityUtils {

    public static double getHumidity(Location location) {
        return location.getWorld().getHumidity(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
