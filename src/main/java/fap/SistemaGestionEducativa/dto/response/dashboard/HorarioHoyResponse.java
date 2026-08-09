package fap.SistemaGestionEducativa.dto.response.dashboard;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "HorarioHoyResponse", description = "Horario del día")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorarioHoyResponse {

    @Schema(example = "08:00 - 10:00")
    private String hora;

    @Schema(example = "Motores Aeronáuticos I")
    private String curso;

    @Schema(example = "Aula 101")
    private String aula;

    @Schema(example = "Carlos Rojas")
    private String docente;

}