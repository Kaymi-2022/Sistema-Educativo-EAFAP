package fap.SistemaGestionEducativa.dto.response.academico;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "CategoriaResponse", description = "Datos de una categoría académica")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponse {

    @Schema(example = "1")
    private Long idCategoria;
    @Schema(example = "ACADEMICO")
    private String nombre;
    @Schema(example = "Formación académica")
    private String descripcion;
    @Schema(example = "Y")
    private String estado;

}
