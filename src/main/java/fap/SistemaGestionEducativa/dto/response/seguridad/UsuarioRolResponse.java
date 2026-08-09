package fap.SistemaGestionEducativa.dto.response.seguridad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "UsuarioRolResponse", description = "Asignación de rol a usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRolResponse {

    @Schema(example = "1")
    private Long idUsuario;
    @Schema(example = "admin")
    private String usuario;
    @Schema(example = "2")
    private Long idRol;
    @Schema(example = "DOCENTE")
    private String rol;

}
