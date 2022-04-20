package com.ad.modules.test.controller;

import com.ad.core.BizController;
import com.ad.modules.test.service.ITdTagService;
import com.ad.modules.test.entity.TdTag;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 
* @author CoderYoung
* Date  2022-04-20
*/
@Api(tags = "")
@RestController
@RequestMapping(value = "/api/test/tdTag")
public class TdTagController extends BizController<ITdTagService,TdTag> {

}