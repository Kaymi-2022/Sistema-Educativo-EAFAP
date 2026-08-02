package fap.SistemaGestionEducativa.dto.request.academico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoRequest {

    @NotBlank(message = "Nombre obligatorio")
    @Size(max = 100)
    private String nombre;

    @Size(max = 200)
    private String descripcion;

    @NotNull(message = "Seleccione una categoría")
    private Long idCategoria;

    @NotNull(message = "Seleccione un docente")
    private Long idDocente;

    @NotNull(message = "Seleccione un periodo")
    private Long idPeriodoAcademico;
}
