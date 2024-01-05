package cn.encmys.ykdz.forest.realthirst.utils;

public class MathUtils {

    public static float minMax(float min, float value, float max) {
        if(min > value) {
            return min;
        } else return Math.min(max, value);
    }
}
