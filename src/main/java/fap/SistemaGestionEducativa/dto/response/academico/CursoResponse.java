package fap.SistemaGestionEducativa.dto.response.academico;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "CursoResponse", description = "Datos de un curso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoResponse {

    @Schema(example = "1")
    private Long idCurso;

    @Schema(example = "Motores Aeronáuticos I")
    private String nombre;

    @Schema(example = "Curso base")
    private String descripcion;

    @Schema(example = "3")
    private Long idCategoria;

    @Schema(example = "ACADEMICO")
    private String categoria;

    @Schema(example = "2")
    private Long idDocente;

    @Schema(example = "Carlos Rojas")
    private String docente;

    @Schema(example = "1")
    private Long idPeriodoAcademico;

    @Schema(example = "2026-I")
    private String periodo;

    @Schema(example = "Y")
    private String estado;

}
