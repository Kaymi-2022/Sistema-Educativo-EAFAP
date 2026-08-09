package fap.SistemaGestionEducativa.dto.response.academico;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "ActividadResponse", description = "Datos de una actividad académica")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActividadResponse {

    @Schema(example = "1")
    private Long idActividad;

    @Schema(example = "Clase Moral")
    private String nombre;

    @Schema(example = "CLASE")
    private String tipo;

    @Schema(example = "1")
    private Long idCurso;

    @Schema(example = "Formación Moral")
    private String curso;

    @Schema(example = "Y")
    private String estado;

}