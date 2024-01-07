package cn.encmys.ykdz.forest.realthirst.utils;

import io.lumine.mythic.lib.api.item.NBTItem;
import io.lumine.mythic.lib.api.player.MMOPlayerData;
import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MMOItemsUtils {

    public static @Nullable String getStringStatValue(@NotNull VolatileMMOItem mmoItem, String statId) {
        NBTItem nbtItem = mmoItem.getNBT();
        return nbtItem.getString("MMOITEMS_" + statId);
    }

    public static double getDoubleStatValue(@NotNull VolatileMMOItem mmoItem, String statId) {
        NBTItem nbtItem = mmoItem.getNBT();
        return nbtItem.getDouble("MMOITEMS_" + statId);
    }

    public static double getPlayerStatValue(Player player, String statId) {
        return MMOPlayerData.get(player.getUniqueId()).getStatMap().getInstance(statId).getTotal();
    }
}
