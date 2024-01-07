package cn.encmys.ykdz.forest.realthirst.player;

import cn.encmys.ykdz.forest.realthirst.RealThirst;
import cn.encmys.ykdz.forest.realthirst.config.MainConfig;
import cn.encmys.ykdz.forest.realthirst.environment.ThirstEnvironment;
import cn.encmys.ykdz.forest.realthirst.event.AridityChangeEvent;
import cn.encmys.ykdz.forest.realthirst.event.ThirstValueChangeEvent;
import cn.encmys.ykdz.forest.realthirst.event.ThirstinessChangeEvent;
import cn.encmys.ykdz.forest.realthirst.hook.MMOItemsHook;
import cn.encmys.ykdz.forest.realthirst.utils.DebugUtils;
import cn.encmys.ykdz.forest.realthirst.utils.MMOItemsUtils;
import cn.encmys.ykdz.forest.realthirst.utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

public class ThirstPlayer {

    private final Player player;
    private final ThirstEnvironment thirstEnvironment;
    private final PersistentDataContainer container;
    private final NamespacedKey thirstValueKey = new NamespacedKey(RealThirst.getPlugin(), "thirst_value");
    private final NamespacedKey thirstinessKey = new NamespacedKey(RealThirst.getPlugin(), "thirstiness");
    private final NamespacedKey aridityKey = new NamespacedKey(RealThirst.getPlugin(), "aridity");

    public ThirstPlayer(Player player) {
        this.player = player;
        this.container = player.getPersistentDataContainer();
        this.thirstEnvironment = new ThirstEnvironment(player.getLocation());
    }

    public void initThirst() {
        if(container.get(thirstValueKey, PersistentDataType.FLOAT) == null) {
            container.set(thirstinessKey, PersistentDataType.FLOAT, MainConfig.max_thirstValue);
        }
        if(container.get(thirstinessKey, PersistentDataType.FLOAT) == null) {
            container.set(thirstinessKey, PersistentDataType.FLOAT, MainConfig.max_thirstValue / 2f);
        }
        if(container.get(aridityKey, PersistentDataType.FLOAT) == null) {
            container.set(aridityKey, PersistentDataType.FLOAT, 0f);
        }
    }

    public void changeThirst(@Nullable ItemStack item, float value) {
        float thirstValue = getThirstValue();
        float thirstiness = getThirstiness();

        if (value > 0) {
            thirstValue += value;
            if (thirstValue > MainConfig.max_thirstValue) {
                thirstiness += thirstValue - MainConfig.max_thirstValue;
                thirstValue = MainConfig.max_thirstValue;
            }
        } else {
            thirstiness += value;
            if (thirstiness < 0) {
                thirstValue += thirstiness;
                thirstiness = 0;
            }
        }

        // call event
        ThirstValueChangeEvent thirstValueChangeEvent = new ThirstValueChangeEvent(
                player,
                item,
                thirstValue
        );
        Bukkit.getPluginManager().callEvent(thirstValueChangeEvent);
        if (!thirstValueChangeEvent.isCancelled()) {
            modifyThirstValue(thirstValueChangeEvent.getNow() - getThirstValue());
        }

        // call event
        ThirstinessChangeEvent thirstinessChangeEvent = new ThirstinessChangeEvent(
                player,
                item,
                thirstiness
        );
        Bukkit.getPluginManager().callEvent(thirstinessChangeEvent);
        if (!thirstinessChangeEvent.isCancelled()) {
            modifyThirstiness(thirstinessChangeEvent.getNow() - getThirstiness());
        }
    }

    public void changeAridity(float value) {

        // call event
        AridityChangeEvent aridityChangeEvent = new AridityChangeEvent(
                player,
                MathUtils.minMax(0f, getAridity() + value, MainConfig.aridity_maxValue)
        );
        Bukkit.getPluginManager().callEvent(aridityChangeEvent);
        if (aridityChangeEvent.isCancelled()) {
            return;
        }

        if(modifyAridity(aridityChangeEvent.getNow() - getAridity()) == MainConfig.aridity_maxValue) {
            modifyAridity(-MainConfig.aridity_maxValue);
            changeThirst(null, -MainConfig.aridity_perThirst * thirstEnvironment.getAridityModifier() * (1 - getWaterKeep()));
        }
    }

    public float modifyThirstValue(float value) {
        DebugUtils.sendPlayerMessage(player, "口渴值" + value);
        float result = MathUtils.minMax(0f, value + getThirstValue(), MainConfig.max_thirstValue);
        container.set(thirstValueKey, PersistentDataType.FLOAT, result);
        return result;
    }

    public float modifyThirstiness(float value) {
        DebugUtils.sendPlayerMessage(player, "干度值" + value);
        float result = MathUtils.minMax(0f, value + getThirstiness(), getThirstValue());
        container.set(thirstinessKey, PersistentDataType.FLOAT, result);
        return result;
    }

    public float modifyAridity(float value) {
        float result = MathUtils.minMax(0f, value + getAridity(), MainConfig.aridity_maxValue);
        container.set(aridityKey, PersistentDataType.FLOAT, result);
        return result;
    }

    public float getThirstValue() {
        return container.getOrDefault(thirstValueKey, PersistentDataType.FLOAT, MainConfig.max_thirstValue);
    }

    public float getThirstiness() {
        return container.getOrDefault(thirstinessKey, PersistentDataType.FLOAT, getThirstValue());
    }

    public float getAridity() {
        return container.getOrDefault(aridityKey, PersistentDataType.FLOAT, 0f);
    }

    public float getWaterKeep() {
        if(!MMOItemsHook.isRegistered()) {
            return 0;
        }
        return (float) MMOItemsUtils.getPlayerStatValue(player, MMOItemsHook.waterKeepId);
    }

    public Player getPlayer() {
        return player;
    }

    public ThirstEnvironment getThirstEnvironment() {
        return thirstEnvironment;
    }
}
