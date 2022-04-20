package com.ad.modules.test.common;

import java.io.File;

/**
 * @author CoderYoung
 */
public class GenerateUtils {

    public static void main(String[] args) {
        String[] tables = {"td_tag"};
        String outPath = new File("modules/test/src/main/java").getAbsolutePath();
        new com.ad.common.generate.GenerateUtils().generateTableCode(
                tables,
                outPath,
                "com.ad.modules",
                "test",
                "jdbc:mysql://192.168.1.8:33336/allcmis?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC",
                "root",
                "123456");

    }

}
