package fap.SistemaGestionEducativa.dto.request.evaluacion;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluacionRequest {
    @NotNull(message = "Seleccione un curso")
    private Long idCurso;

    @NotBlank(message = "Nombre obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "Tipo obligatorio")
    private String tipo;

    @NotNull(message = "Ingrese el peso")
    @DecimalMin(value = "1")
    @DecimalMax(value = "100")
    private BigDecimal peso;

    @NotNull(message = "Ingrese la fecha")
    private LocalDate fecha;
}
