package com.ad.core.starter.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * @author CoderYoung
 */
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket systemApiDoc() {
        return buildDocket("com.ad.core.system", "system");
    }

    @Bean
    public Docket testApiDoc() {
        return buildDocket("com.ad.modules.test", "test");
    }


    private Docket buildDocket(String basePackage, String group) {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(new ApiInfoBuilder()
                        .title("AllDimensions")
                        .description("接口说明")
                        .version("0.0.1")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .groupName(group)
                .securitySchemes(getSecuritySchemes())
                .securityContexts(getSecurityContexts());
    }

    private List<SecurityScheme> getSecuritySchemes() {
        return Collections.singletonList(new ApiKey("Authorization", "Authorization", "header"));
    }

    private List<SecurityContext> getSecurityContexts() {
        AuthorizationScope[] authorizationScopes = {new AuthorizationScope("global", "accessEverything")};
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference("Authorization", authorizationScopes)))
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        );
    }


}
