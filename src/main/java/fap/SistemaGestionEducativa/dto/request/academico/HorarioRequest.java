package fap.SistemaGestionEducativa.dto.request.academico;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioRequest {

    @NotBlank(message = "Seleccione un día")
    private String diaSemana;

    @NotNull(message = "Seleccione la fecha")
    private LocalDate fecha;

    @NotNull(message = "Seleccione la semana")
    private Long idSemana;

    @NotNull(message = "Seleccione el aula")
    private Long idAula;

    @NotNull(message = "Seleccione el bloque")
    private Long idBloque;

    @NotNull(message = "Seleccione la actividad")
    private Long idActividad;

    @NotNull(message = "Seleccione el instructor")
    private Long idInstructor;
}
