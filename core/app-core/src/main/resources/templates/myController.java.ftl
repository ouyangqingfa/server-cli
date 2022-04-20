package ${package.Controller};

import com.ad.core.BizController;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* ${table.comment}
* @author ${author}
* Date  ${date}
*/
@Api(tags = "${table.comment!}")
@RestController
@RequestMapping(value = "/api<#if package.ModuleName?? && package.ModuleName ?length gt 0 >/${package.ModuleName}</#if>/${table.entityPath}")
public class ${table.controllerName} extends BizController<${table.serviceName},${entity}> {

}