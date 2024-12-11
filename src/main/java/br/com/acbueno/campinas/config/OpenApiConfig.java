package br.com.acbueno.campinas.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder().group("rent-bikes-api").pathsToMatch("/api/**").build();
  }

  @Bean
  public OpenAPI customOpenApi() {
    return new OpenAPI().info(new Info().title("Rent Bike Api").version("1.0")
        .description("API RESTful for managing bikes")
        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));

  }

}
