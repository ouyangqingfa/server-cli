package com.ad.common.enums;

/**
 * 时间周期枚举
 * @author CoderYoung
 */
public enum EnumQuaque {
    UNKNOWN("unknown"),
    HOUR("hour"),
    DAY("day"),
    WEEK("week"),
    MONTH("month");

    private String key;

    public String getKey() {
        return key;
    }

    EnumQuaque(String key) {
        this.key = key;
    }

    public static EnumQuaque getQuaqueByKey(String key) {
        EnumQuaque[] enums = EnumQuaque.values();
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].key.equals(key)) {
                return enums[i];
            }
        }
        return EnumQuaque.UNKNOWN;
    }

    @Override
    public String toString() {
        return key;
    }
}
