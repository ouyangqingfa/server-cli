package com.ad.modules.test.controller;

import com.ad.core.BaseResult;
import com.ad.core.BizController;
import com.ad.modules.test.entity.AdsCountry;
import com.ad.modules.test.service.AdsCountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CoderYoung
 * @since 2021-11-11
 */
@Api(tags = "adsCountry")
@RestController
@RequestMapping("/adsCountry")
public class AdsCountryController extends BizController<AdsCountryService, AdsCountry> {

    @ApiOperation("helloWorld")
    @GetMapping("helloWorld")
    public BaseResult<String> helloWorld(@RequestParam String msg) {
        if (StringUtils.isEmpty(msg)) {
            msg = "helloWorld";
        }
        return buildResult(msg);
    }

}

