package ${package.Controller};

import com.ad.core.BaseController;
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
public class ${table.controllerName} extends BaseController {

}