package cn.encmys.ykdz.forest.realthirst.listener.hook;

import cn.encmys.ykdz.forest.realthirst.config.MainConfig;
import cn.encmys.ykdz.forest.realthirst.hook.MMOItemsHook;
import cn.encmys.ykdz.forest.realthirst.player.ThirstPlayer;
import cn.encmys.ykdz.forest.realthirst.utils.MMOItemsUtils;
import net.Indyuce.mmoitems.api.event.item.ConsumableConsumedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MMOItemsListener implements Listener {

    @EventHandler
    public void onPlayerDrinkWater(ConsumableConsumedEvent e) {
        ThirstPlayer thirstPlayer = new ThirstPlayer(e.getPlayer());
        if(thirstPlayer.getThirstValue() == MainConfig.max_thirstValue) { e.setCancelled(true); }
        String purity = MMOItemsUtils.getStringStatValue(e.getMMOItem(), MMOItemsHook.waterPurityId);
        if(purity == null) { return; }
        try {
            thirstPlayer.changeThirst(e.getMMOItem().getNBT().toItem(), Float.parseFloat(purity));
        } catch (NumberFormatException ignored) {
            thirstPlayer.changeThirst(e.getMMOItem().getNBT().toItem(), MainConfig.getPurityValue(purity));
        }
    }
}
