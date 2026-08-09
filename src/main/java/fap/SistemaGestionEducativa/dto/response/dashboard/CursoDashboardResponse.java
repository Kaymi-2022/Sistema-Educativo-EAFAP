package fap.SistemaGestionEducativa.dto.response.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "CursoDashboardResponse", description = "Curso mostrado en el dashboard")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoDashboardResponse {

    @Schema(example = "1")
    private Long idCurso;

    @Schema(example = "Motores Aeronáuticos I")
    private String curso;

    @Schema(example = "ACADEMICO")
    private String categoria;

    @Schema(example = "Carlos Rojas")
    private String docente;

    @Schema(example = "25")
    private Integer totalEstudiantes;

}