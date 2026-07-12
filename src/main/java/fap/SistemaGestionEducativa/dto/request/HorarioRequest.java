package fap.SistemaGestionEducativa.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class HorarioRequest {

    @NotBlank(message = "El día de la semana no puede estar vacío")
    private String diaSemana;

    private Long idSemana;

    private Long idAula;

    private Long idBloqueHorario;

    private Long idActividad;

    private Long idUsuario;
}
