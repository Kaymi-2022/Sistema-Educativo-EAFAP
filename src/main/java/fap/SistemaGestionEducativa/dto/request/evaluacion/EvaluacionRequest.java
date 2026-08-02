package fap.SistemaGestionEducativa.dto.request.evaluacion;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

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
    @Min(value = 1)
    @Max(value = 20)
    private int peso;

    @NotNull(message = "Ingrese la fecha")
    private LocalDate fecha;
}
