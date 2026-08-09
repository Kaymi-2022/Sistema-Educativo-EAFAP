package fap.SistemaGestionEducativa.dto.response.dashboard;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "ResumenCategoriaResponse", description = "Resumen de cursos por categoría")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumenCategoriaResponse {

    @Schema(example = "ACADEMICO")
    private String categoria;

    @Schema(example = "3")
    private Integer totalCursos;

}