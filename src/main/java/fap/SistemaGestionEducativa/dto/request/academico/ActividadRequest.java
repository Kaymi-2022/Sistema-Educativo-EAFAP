package fap.SistemaGestionEducativa.dto.request.academico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActividadRequest {

    @NotBlank(message = "Nombre obligatorio")
    private String nombre;

    @NotBlank(message = "Tipo obligatorio")
    private String tipo;

    @NotNull(message = "Seleccione un curso")
    private Long idCurso;

}
