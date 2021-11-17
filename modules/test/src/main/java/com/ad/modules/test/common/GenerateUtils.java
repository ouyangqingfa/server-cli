package com.ad.modules.test.common;

import java.io.File;

/**
 * @author CoderYoung
 */
public class GenerateUtils {

    public static void main(String[] args) {
        String[] tables = {"ads_country"};
        String outPath = new File("modules/test/src/main/java").getAbsolutePath();
        new com.ad.common.generate.GenerateUtils().generateTableCode(
                tables,
                outPath,
                "com.ad.modules.test",
                "jdbc:mysql://192.168.4.223:3306/adts?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC",
                "root",
                "123456");

    }

}
