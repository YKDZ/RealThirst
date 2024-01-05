package cn.encmys.ykdz.forest.realthirst.config;

import cn.encmys.ykdz.forest.realthirst.RealThirst;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainConfig {
    private static Plugin plugin = RealThirst.getPlugin();
    private static YamlConfiguration config;
    public static float max_thirstValue;
    // aridity
    public static float aridity_maxValue;
    public static float aridity_perThirst;
    public static float aridity_actions_swimming;
    public static float aridity_actions_breakBlock;
    public static float aridity_actions_sprinting;
    public static float aridity_actions_jumping;
    public static float aridity_actions_jumpingWhenSprinting;
    public static float aridity_actions_attackEntity;
    // version
    public static int version;

    public static void load() {

        File file = new File(plugin.getDataFolder(), "config.yml");
        config = new YamlConfiguration();

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource("config.yml", false);
        }

        try {
            config.load(file);
            loadSettings();
        } catch (IOException | InvalidConfigurationException error) {
            error.printStackTrace();
        }
    }

    private static void loadSettings() {
        ConfigurationSection section = null;
        // max-thirstValue
        max_thirstValue = (float) config.getDouble("max-thirstValue");
        // aridity
        section = config.getConfigurationSection("aridity");
        aridity_maxValue = (float) section.getDouble("max-value");
        aridity_perThirst = (float) section.getDouble("per-thirst");
        // aridity.actions
        section = config.getConfigurationSection("aridity.actions");
        aridity_actions_swimming = (float) section.getDouble("swimming");
        aridity_actions_breakBlock = (float) section.getDouble("break-block");
        aridity_actions_sprinting = (float) section.getDouble("sprinting");
        aridity_actions_jumping = (float) section.getDouble("jumping");
        aridity_actions_jumpingWhenSprinting = (float) section.getDouble("jumping-when-sprinting");
        aridity_actions_attackEntity = (float) section.getDouble("attack-entity");
        // version
        version = config.getInt("version");
    }
}
