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
@RequestMapping(value = "/api/${table.entityPath}")
public class ${table.controllerName} extends BizController<${table.serviceName},${entity}> {

}