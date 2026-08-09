package fap.SistemaGestionEducativa.dto.response.evaluacion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(name = "NotaResponse", description = "Datos de una nota")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotaResponse {

    @Schema(example = "1")
    private Long idNota;

    @Schema(example = "1")
    private Long idEvaluacion;

    @Schema(example = "Parcial")
    private String evaluacion;

    @Schema(example = "4")
    private Long idDiscente;

    @Schema(example = "Juan Pérez")
    private String discente;

    @Schema(example = "18.00")
    private BigDecimal calificacion;

    @Schema(example = "Excelente")
    private String observacion;

    @Schema(example = "Y")
    private String estado;

}