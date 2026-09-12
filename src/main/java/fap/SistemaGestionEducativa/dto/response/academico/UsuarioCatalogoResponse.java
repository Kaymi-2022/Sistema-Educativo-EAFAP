package fap.SistemaGestionEducativa.dto.response.academico;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "UsuarioCatalogoResponse", description = "Usuario activo con un rol académico específico")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCatalogoResponse {
    private Long idUsuario;
    private String dni;
    private String nombres;
    private String apellidos;
    private String username;
}
