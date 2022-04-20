package com.ad.core.system.common;

import java.io.File;

/**
 * @author CoderYoung
 */
public class GenerateUtils {

    public static void main(String[] args) {
        String[] tables = {"sys_menus", "sys_role_menus", "sys_roles", "sys_user", "sys_user_menus", "sys_user_role"};
        String outPath = new File("core/app-system/src/main/java").getAbsolutePath();
        new com.ad.common.generate.GenerateUtils().generateTableCode(
                tables,
                outPath,
                "com.ad.core", "system",
                "jdbc:mysql://192.168.4.223:3306/adserver?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC",
                "root",
                "123456");
    }

}
