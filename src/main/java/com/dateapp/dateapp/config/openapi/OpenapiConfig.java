package com.dateapp.dateapp.config.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Date App",
                description = "Dating application api",
                contact = @Contact(
                        email = "mariuszo19@onet.pl"),
                version = "1.0"
        ),
        security = @SecurityRequirement(name = "JWTSecurity")

)
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "JWTSecurity",
        description = "Register new user and login to get authentication token in response",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)


public class OpenapiConfig {
}
