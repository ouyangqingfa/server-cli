package com.ad.core.system.controller;

import com.ad.common.utils.DateEx;
import com.ad.core.BaseController;
import com.ad.core.BaseResult;
import com.ad.core.system.auth.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CoderYoung
 */
@Api(tags = "Development")
@RestController
@RequestMapping("/api/dev")
public class SystemDevTestController extends BaseController {

    @ApiOperation("hello")
    @GetMapping("hello")
    public BaseResult<String> helloWorld() {
        return buildResult("Server Say Hello On " + new DateEx().format());
    }


    @ApiOperation("开发调试Token")
    @GetMapping("getDevToken")
    public BaseResult<String> getDevToken(String uid) {
        return buildResult(JwtUtil.sign(uid, 30));
    }

}
