package fap.SistemaGestionEducativa.dto.response.evaluacion;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(name = "ResultadoCursoResponse", description = "Resultado final por curso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoCursoResponse {

    @Schema(example = "1")
    private Long idResultado;

    @Schema(example = "3")
    private Long idCurso;

    @Schema(example = "Motores Aeronáuticos I")
    private String curso;

    @Schema(example = "4")
    private Long idDiscente;

    @Schema(example = "Juan Pérez")
    private String discente;

    @Schema(example = "18.00")
    private BigDecimal promedioFinal;

    @Schema(example = "A")
    private String estadoAprobacion;

    @Schema(example = "2026-08-09")
    private LocalDate fechaCierre;

    @Schema(example = "Y")
    private String estado;

}
