package cn.encmys.ykdz.forest.realthirst.listener.hook;

import net.Indyuce.mmoitems.api.event.item.ConsumableConsumedEvent;
import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MMOItemsListener implements Listener {

    @EventHandler
    public void onPlayerDrinkWater(ConsumableConsumedEvent e) {
        VolatileMMOItem item = e.getMMOItem();
    }
}
