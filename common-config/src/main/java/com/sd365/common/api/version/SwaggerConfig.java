/**
 * @file: BaseSwaggerConfig
 * @author :zhengzhicheng
 * @date:2020/8/27 13:48
 * @copyright:2020-2023 www.bosssoft.com.cn Inc. All rights reserved
 */
package com.sd365.common.api.version;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @version 1.0.0
 * @class: BaseSwaggerConfig
 * @classdesc Swagger 基础配置
 * @Author zhengzhicheng
 * @date 2020/8/27 13:48
 * @see
 * @since
 */
@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi( MySwaggerProperties swaggerProperties) {
        log.info("SwaggerConfig.createRestApi call");
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getApiBasePackage()))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo(swaggerProperties));
    }

    private ApiInfo apiInfo(MySwaggerProperties swaggerProperties) {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }

}
