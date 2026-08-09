package fap.SistemaGestionEducativa.dto.response.academico;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Schema(name = "CursoDiscenteResponse", description = "Datos de una matrícula")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoDiscenteResponse {

    @Schema(example = "1")
    private Long idCursoDiscente;

    @Schema(example = "3")
    private Long idCurso;

    @Schema(example = "Motores Aeronáuticos I")
    private String curso;

    @Schema(example = "4")
    private Long idDiscente;

    @Schema(example = "Juan Pérez")
    private String discente;

    @Schema(example = "2026-08-09")
    private LocalDate fechaMatricula;

    @Schema(example = "Y")
    private String estado;

}
