package fap.SistemaGestionEducativa.dto.request.academico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SemanaAcademicaRequest {

    @NotNull(message = "Número de semana obligatorio")
    @Positive
    private Integer numeroSemana;

    @Size(max = 100)
    private String descripcion;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;
}
