package cn.encmys.ykdz.forest.realthirst.hook;

import cn.encmys.ykdz.forest.realthirst.config.MainConfig;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.stat.type.DoubleStat;
import net.Indyuce.mmoitems.stat.type.StringStat;
import org.bukkit.Material;

public class MMOItemsHook {
    public static final String waterPurityId = "REALTHIRST_WATER_PURITY";
    public static final String waterKeepId = "REALTHIRST_WATER_KEEP";
    private static boolean isRegistered = false;

    public static void register() {
        StringStat water_purity = new StringStat(waterPurityId,
                MainConfig.mmoitems_stats_waterPurity_material,
                MainConfig.mmoitems_stats_waterPurity_name,
                MainConfig.mmoitems_stats_waterPurity_lore,
                MainConfig.mmoitems_stats_waterPurity_types,
                new Material[0]);
        DoubleStat water_keep = new DoubleStat(waterKeepId,
                MainConfig.mmoitems_stats_waterKeep_material,
                MainConfig.mmoitems_stats_waterKeep_name,
                MainConfig.mmoitems_stats_waterKeep_lore,
                MainConfig.mmoitems_stats_waterKeep_types,
                new Material[0]);
        MMOItems.plugin.getStats().register(water_purity);
        MMOItems.plugin.getStats().register(water_keep);
        isRegistered =  true;
    }

    public static boolean isRegistered() {
        return isRegistered;
    }
}
