package fap.SistemaGestionEducativa.dto.response.academico;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Schema(name = "BloqueHorarioResponse", description = "Datos de un bloque horario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloqueHorarioResponse {

    @Schema(example = "1")
    private Long idBloque;

    @Schema(example = "08:00")
    private String horaInicio;

    @Schema(example = "10:00")
    private String horaFin;

    @Schema(example = "Y")
    private String estado;

}