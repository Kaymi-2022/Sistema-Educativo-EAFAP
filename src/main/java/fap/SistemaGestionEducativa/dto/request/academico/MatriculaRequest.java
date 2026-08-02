package fap.SistemaGestionEducativa.dto.request.academico;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaRequest {
    @NotNull(message = "Seleccione un curso")
    private Long idCurso;

    @NotNull(message = "Seleccione un estudiante")
    private Long idDiscente;
}
