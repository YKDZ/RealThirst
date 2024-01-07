package cn.encmys.ykdz.forest.realthirst.utils;

import cn.encmys.ykdz.forest.realthirst.RealThirst;
import me.casperge.realisticseasons.api.SeasonsAPI;
import org.bukkit.Location;

public class TemperatureUtils {

    public static double getTemperature(Location location) {
        if(RealThirst.isHookedPluginEnabled("RealisticSeasons")) {
            return SeasonsAPI.getInstance().getAirTemperature(location);
        }
        return (100 * location.getWorld().getTemperature(location.getBlockX(), location.getBlockY(), location.getBlockZ())) - 50;
    }
}
