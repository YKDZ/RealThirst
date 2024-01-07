package cn.encmys.ykdz.forest.realthirst.environment;

import cn.encmys.ykdz.forest.realthirst.RealThirst;
import cn.encmys.ykdz.forest.realthirst.config.MainConfig;
import cn.encmys.ykdz.forest.realthirst.utils.HumidityUtils;
import cn.encmys.ykdz.forest.realthirst.utils.MathUtils;
import cn.encmys.ykdz.forest.realthirst.utils.TemperatureUtils;
import org.bukkit.Location;

import java.util.HashMap;

public class ThirstEnvironment {

    private final Location location;

    public ThirstEnvironment(Location location) {
        this.location = location;
    }

    public float getAridityModifier() {
        return MathUtils.getFormulaResult(MainConfig.environment_formula, new HashMap<String, String>() {{
            put("airTemp", String.valueOf(TemperatureUtils.getTemperature(location)));
            put("humidity", String.valueOf(HumidityUtils.getHumidity(location)));
        }});
    }
}
