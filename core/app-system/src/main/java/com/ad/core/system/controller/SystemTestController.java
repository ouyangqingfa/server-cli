package com.ad.core.system.controller;

import com.ad.common.utils.DateEx;
import com.ad.core.BaseController;
import com.ad.core.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CoderYoung
 */
@Api(tags = "test")
@RestController
@RequestMapping("/api/system/test")
public class SystemTestController extends BaseController {

    @ApiOperation("hello")
    @GetMapping("hello")
    public BaseResult<String> helloWorld() {
        return buildResult("Server Say Hello On " + new DateEx().format());
    }

}
