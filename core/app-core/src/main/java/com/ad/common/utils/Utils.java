package com.ad.common.utils;

/**
 * @author CoderYoung
 */
public class Utils {

    public static double getMinVal(double... vals) {
        double min = vals[0];
        for (double val : vals) {
            min = Math.min(min, val);
        }
        return min;
    }

    public static double getMaxVal(double... vals) {
        double max = vals[0];
        for (double val : vals) {
            max = Math.max(max, val);
        }
        return max;
    }

}
