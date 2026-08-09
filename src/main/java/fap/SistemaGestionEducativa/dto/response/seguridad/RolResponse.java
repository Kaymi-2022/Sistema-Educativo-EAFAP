package fap.SistemaGestionEducativa.dto.response.seguridad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "RolResponse", description = "Datos de un rol")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolResponse {

    @Schema(example = "1")
    private Long idRol;
    @Schema(example = "DOCENTE")
    private String nombreRol;
    @Schema(example = "Y")
    private String estado;
}
