package fap.SistemaGestionEducativa.dto.response.academico;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "AulaResponse", description = "Datos de un aula")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AulaResponse {

    @Schema(example = "1")
    private Long idAula;

    @Schema(example = "Aula 101")
    private String nombre;

    @Schema(example = "30")
    private Integer capacidad;

    @Schema(example = "Pabellón A")
    private String ubicacion;

    @Schema(example = "Y")
    private String estado;

}