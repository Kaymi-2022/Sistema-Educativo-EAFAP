package fap.SistemaGestionEducativa.dto.response.dashboard;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Schema(name = "EvaluacionPendienteResponse", description = "Evaluación pendiente en el dashboard")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionPendienteResponse {

    @Schema(example = "1")
    private Long idEvaluacion;

    @Schema(example = "Parcial")
    private String evaluacion;

    @Schema(example = "Motores Aeronáuticos I")
    private String curso;

    @Schema(example = "2026-09-15")
    private LocalDate fecha;

    @Schema(example = "Carlos Rojas")
    private String docente;

}
