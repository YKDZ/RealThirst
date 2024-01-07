package cn.encmys.ykdz.forest.realthirst.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

public class MathUtils {

    public static float minMax(float min, float value, float max) {
        if(min > value) {
            return min;
        } else return Math.min(max, value);
    }

    public static float getFormulaResult(String formula, HashMap<String, String> vars) {
        formula = parseVariables(formula, vars);
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try {
            Object result = engine.eval(formula);
            return Float.parseFloat(String.valueOf(result));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return 0f;
    }

    public static String parseVariables(String text, HashMap<String, String> varMap) {
        for (Map.Entry<String, String> entry : varMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            text = text.replace("%" + key + "%", value);
        }
        return text;
    }
}
