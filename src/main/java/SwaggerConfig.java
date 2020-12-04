import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;



import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.*;

@Configuration
@EnableOpenApi
public class SwaggerConfig  {

//    @Bean
//    public Docket api() {
////        Server serverLocal = new Server("local", "http://localhost:8080", "for local usages", Collections.emptyList(), Collections.emptyList());
////        Server testServer = new Server("test", "http://localhost:8080", "for testing", Collections.emptyList(), Collections.emptyList());
//        return new Docket(DocumentationType.OAS_30)
////                .servers(serverLocal, testServer)
////                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("ua.com.foxminded.controller"))
////                .paths(PathSelectors.regex("/student.*"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//    
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Openapi OAS3 with springfox ")
//                .description("Code first approach")
//                .version("1.0.0")
//                .contact(new Contact("Nata", "http://localhost:8080/university/about/", "svetnata74@mail.ru"))
//                .build();
//    }

}