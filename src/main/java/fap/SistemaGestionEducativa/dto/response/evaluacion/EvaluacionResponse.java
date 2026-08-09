package fap.SistemaGestionEducativa.dto.response.evaluacion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(name = "EvaluacionResponse", description = "Datos de una evaluación")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionResponse {

    @Schema(example = "1")
    private Long idEvaluacion;

    @Schema(example = "3")
    private Long idCurso;

    @Schema(example = "Motores Aeronáuticos I")
    private String curso;

    @Schema(example = "Parcial")
    private String nombre;

    @Schema(example = "EXAMEN")
    private String tipo;

    @Schema(example = "40")
    private BigDecimal peso;

    @Schema(example = "2026-09-15")
    private LocalDate fecha;

    @Schema(example = "Y")
    private String estado;

}
