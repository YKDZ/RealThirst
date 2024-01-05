package cn.encmys.ykdz.forest.realthirst.player;

import cn.encmys.ykdz.forest.realthirst.RealThirst;
import cn.encmys.ykdz.forest.realthirst.config.MainConfig;
import cn.encmys.ykdz.forest.realthirst.utils.MathUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ThirstPlayer {

    private final Player player;
    private final PersistentDataContainer container;
    private final NamespacedKey thirstValueKey = new NamespacedKey(RealThirst.getPlugin(), "thirst_value");
    private final NamespacedKey thirstinessKey = new NamespacedKey(RealThirst.getPlugin(), "thirstiness");
    private final NamespacedKey aridityKey = new NamespacedKey(RealThirst.getPlugin(), "aridity");

    public ThirstPlayer(Player player) {
        this.player = player;
        this.container = player.getPersistentDataContainer();
    }

    public void initThirst() {
        if(container.get(thirstValueKey, PersistentDataType.FLOAT) == null) {
            container.set(thirstinessKey, PersistentDataType.FLOAT, MainConfig.max_thirstValue);
        }
        if(container.get(thirstinessKey, PersistentDataType.FLOAT) == null) {
            container.set(thirstinessKey, PersistentDataType.FLOAT, MainConfig.max_thirstValue);
        }
        if(container.get(aridityKey, PersistentDataType.FLOAT) == null) {
            container.set(aridityKey, PersistentDataType.FLOAT, 0f);
        }
    }

    public void changeThirst(float value) {
        float remain;
        if(value > 0) {
            remain = value + getThirstValue() - MainConfig.max_thirstValue;
            modifyThirstValue(value);
            if(remain > 0) {
                modifyThirstiness(remain);
            }
        } else {
            remain = getThirstiness() + value;
            modifyThirstiness(value);
            if(remain < 0) {
                modifyThirstValue(remain);
            }
        }
    }

    public void changeAridity(float value) {
        if(modifyAridity(value) == MainConfig.aridity_maxValue) {
            modifyAridity(-MainConfig.aridity_maxValue);
            changeThirst(MainConfig.aridity_perThirst);
        }
    }

    public float modifyThirstValue(float value) {
        float result = MathUtils.minMax(0f, value + getThirstValue(), MainConfig.max_thirstValue);
        container.set(thirstValueKey, PersistentDataType.FLOAT, result);
        return result;
    }

    public float modifyThirstiness(float value) {
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

    public Player getPlayer() {
        return player;
    }
}
