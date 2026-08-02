package fap.SistemaGestionEducativa.dto.request.evaluacion;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotaRequest {
    @NotNull(message = "Seleccione la evaluación")
    private Long idEvaluacion;

    @NotNull(message = "Seleccione el estudiante")
    private Long idDiscente;

    @NotNull(message = "Ingrese la nota")
    @DecimalMin(value = "0")
    @DecimalMax(value = "20")
    private BigDecimal calificacion;

    @Size(max = 200)
    private String observacion;
}
