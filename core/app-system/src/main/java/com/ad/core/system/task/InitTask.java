package com.ad.core.system.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.ad.common.utils.DateEx;
import com.ad.core.system.common.Constant;
import com.ad.core.system.entity.SysUser;
import com.ad.core.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


/**
 * @author CoderYoung
 */
@Component
public class InitTask implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(InitTask.class);

    @Lazy
    @Autowired
    private ISysUserService userService;

    @Override
    public void run(String... args) throws Exception {
        checkAdminUser();
    }


    /**
     * 超级管理员
     */
    private void checkAdminUser() {
        SysUser adminUser = userService.getUserByUserId(Constant.ADMIN_ACCOUNT);
        if (adminUser == null) {
            adminUser = new SysUser();
            adminUser.setUid(Constant.ADMIN_ACCOUNT);
            adminUser.setUname("超级系统管理员");
            String userSign = IdUtil.fastSimpleUUID();
            adminUser.setSign(userSign);
            adminUser.setPwd(SecureUtil.sha1(Constant.ADMIN_PASSWORD + userSign));
            adminUser.setRemark(String.format("%s 自动添加超级系统管理员", new DateEx().format()));
            userService.save(adminUser);
        }
    }

}
