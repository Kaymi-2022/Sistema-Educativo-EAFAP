package fap.SistemaGestionEducativa.dto.response.academico;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorarioResponse {

    private Long idHorario;

    private String diaSemana;

    private LocalDate fecha;

    private Long idSemana;

    private Integer numeroSemana;

    private Long idAula;

    private String aula;

    private Long idBloque;

    private String bloqueHorario;

    private Long idActividad;

    private String actividad;

    private Long idInstructor;

    private String instructor;

    private String estado;

}
