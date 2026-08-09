package fap.SistemaGestionEducativa.dto.response.academico;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Schema(name = "HorarioResponse", description = "Datos de un horario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorarioResponse {

    @Schema(example = "1")
    private Long idHorario;

    @Schema(example = "LUNES")
    private String diaSemana;

    @Schema(example = "2026-09-01")
    private LocalDate fecha;

    @Schema(example = "1")
    private Long idSemana;

    @Schema(example = "1")
    private Integer numeroSemana;

    @Schema(example = "1")
    private Long idAula;

    @Schema(example = "Aula 101")
    private String aula;

    @Schema(example = "1")
    private Long idBloque;

    @Schema(example = "08:00 - 10:00")
    private String bloqueHorario;

    @Schema(example = "1")
    private Long idActividad;

    @Schema(example = "Clase Moral")
    private String actividad;

    @Schema(example = "2")
    private Long idInstructor;

    @Schema(example = "Carlos Rojas")
    private String instructor;

    @Schema(example = "Y")
    private String estado;

}
