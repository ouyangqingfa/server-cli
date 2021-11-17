package com.ad.core.system.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author CoderYoung
 */
@Configuration
public class SystemSwaggerConfig {

    @Bean
    public Docket systemApiDoc() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(new ApiInfoBuilder()
                        .title("AllDimensions")
                        .description("接口说明")
                        .version("0.0.1")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ad.core.system"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .groupName("system");
    }

}
