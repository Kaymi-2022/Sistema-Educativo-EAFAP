package fap.SistemaGestionEducativa.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Sistema de Gestión Educativa",
                version = "1.0",
                description = "Documentación OpenAPI del sistema"
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor local")
        }
)
public class OpenApiConfig {
}
