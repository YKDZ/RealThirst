package cn.encmys.ykdz.forest.realthirst.config;

import cn.encmys.ykdz.forest.realthirst.RealThirst;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class MainConfig {

    private static Plugin plugin = RealThirst.getPlugin();
    private static YamlConfiguration config;
    // debug
    public static boolean debug;
    // bStats
    public static boolean bStats;
    public static float max_thirstValue;
    // aridity
    public static float aridity_maxValue;
    public static float aridity_perThirst;
    // aridity.actions
    public static float aridity_actions_swimming;
    public static float aridity_actions_breakBlock;
    public static float aridity_actions_sprinting;
    public static float aridity_actions_jumping;
    public static float aridity_actions_jumpingWhenSprinting;
    public static float aridity_actions_attackEntity;
    // environment
    public static String environment_formula;
    // effect
    public static float effect_disableSatiated;
    public static float effect_disableJumping;
    // mmoitems.stats
    public static String mmoitems_stats_waterPurity_name;
    public static Material mmoitems_stats_waterPurity_material;
    public static String[] mmoitems_stats_waterPurity_lore;
    public static String[] mmoitems_stats_waterPurity_types;
    public static String mmoitems_stats_waterKeep_name;
    public static Material mmoitems_stats_waterKeep_material;
    public static String[] mmoitems_stats_waterKeep_lore;
    public static String[] mmoitems_stats_waterKeep_types;
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
        // debug
        debug = config.getBoolean("debug", false);
        // bStats
        bStats = config.getBoolean("bStats", true);
        // max-thirstValue
        max_thirstValue = (float) config.getDouble("max-thirstValue", 20);
        // aridity
        section = config.getConfigurationSection("aridity");
        aridity_maxValue = (float) section.getDouble("max-value", 4);
        aridity_perThirst = (float) section.getDouble("per-thirst", 1);
        // aridity.actions
        section = config.getConfigurationSection("aridity.actions");
        aridity_actions_swimming = (float) section.getDouble("swimming", 0.01);
        aridity_actions_breakBlock = (float) section.getDouble("break-block", 0.005);
        aridity_actions_sprinting = (float) section.getDouble("sprinting", 0.1);
        aridity_actions_jumping = (float) section.getDouble("jumping", 0.05);
        aridity_actions_jumpingWhenSprinting = (float) section.getDouble("jumping-when-sprinting", 0.2);
        aridity_actions_attackEntity = (float) section.getDouble("attack-entity", 0.1);
        // environment
        section = config.getConfigurationSection("environment");
        environment_formula = section.getString("formula", "0.5 + (%airTemp% + 50) / 100 * (3 - 0.5) * (1 - %humidity%)");
        // effect
        section = config.getConfigurationSection("effect");
        effect_disableSatiated = (float) section.getDouble("disable-satiated",  6);
        effect_disableJumping = (float) section.getDouble("disable-jumping", 2.0);
        // mmoitems.stats
        section = config.getConfigurationSection("mmoitems.stats");
        mmoitems_stats_waterPurity_material = Material.valueOf(section.getString("water-purity.material", "WATER_BUCKET"));
        mmoitems_stats_waterPurity_name = section.getString("water-purity.name", "水质纯净度");
        mmoitems_stats_waterPurity_lore = section.getStringList("water-purity.lore").toArray(new String[0]);
        mmoitems_stats_waterPurity_types = section.getStringList("water-purity.types").toArray(new String[0]);

        mmoitems_stats_waterKeep_material = Material.valueOf(section.getString("water-keep.material", "LEATHER_CHESTPLATE"));
        mmoitems_stats_waterKeep_name = section.getString("water-keep.name", "保水性");
        mmoitems_stats_waterKeep_lore = section.getStringList("water-keep.lore").toArray(new String[0]);
        mmoitems_stats_waterKeep_types = section.getStringList("water-keep.types").toArray(new String[0]);
        // version
        version = config.getInt("version");
    }

    public static float getPurityValue(String purity) {
        return (float) config.getDouble("mmoitems.purity." + purity.toLowerCase(), 0);
    }
}
