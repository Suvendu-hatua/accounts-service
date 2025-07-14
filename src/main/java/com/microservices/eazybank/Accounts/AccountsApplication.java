package com.microservices.eazybank.Accounts;

import com.microservices.eazybank.Accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Accounts Microservices Rest API Documentation",
        description = "Banking: Accounts microservice REST API Documentation",
        version = "v1",
        contact = @Contact(
            name = "Suvendu Hatua",
            email = "iamsuvenduhatua@gmail.com",
            url = "www.google.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "http://localhost:8080/swagger-ui/index.html"
        )
    ),
    externalDocs = @ExternalDocumentation(
        description = "Banking: Accounts microservice REST API Documentation",
        url = "http://localhost:8080/swagger-ui/index.html"
    )
)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(AccountsContactInfoDto.class)
public class AccountsApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountsApplication.class, args);
  }

}
