package ${package.Controller};

import com.ad.core.BaseController;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
* ${table.comment}
* @author ${author}
* Date  ${date}
*/
@Api(tags = "${table.comment!}")
@RestController
@RequestMapping(value = "/api<#if package.ModuleName?? && package.ModuleName ?length gt 0 >/${package.ModuleName}</#if>/${table.entityPath}")
public class ${table.controllerName} extends BaseController {

    @Autowired
    private ${table.serviceName} baseService;

}