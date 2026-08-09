package fap.SistemaGestionEducativa.dto.response.seguridad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "UsuarioResponse", description = "Datos de un usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    @Schema(example = "1")
    private Long idUsuario;
    @Schema(example = "45678901")
    private String dni;
    @Schema(example = "Juan")
    private String nombres;
    @Schema(example = "Pérez")
    private String apellidos;
    @Schema(example = "juan@eafap.mil.pe")
    private String email;
    @Schema(example = "juanperez")
    private String username;
    @Schema(example = "Y")
    private String estado;

}