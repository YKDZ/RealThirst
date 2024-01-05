package cn.encmys.ykdz.forest.realthirst.hook;

import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.stat.type.StringStat;
import org.bukkit.Material;

public class MMOItemsHook {

    public static void register() {
        StringStat water_purity = new StringStat("realthirst_water_purity",
                Material.WATER_BUCKET,
                "水质纯净度",
                null,
                null,
                (Material) null);
        MMOItems.plugin.getStats().register(water_purity);
    }
}
