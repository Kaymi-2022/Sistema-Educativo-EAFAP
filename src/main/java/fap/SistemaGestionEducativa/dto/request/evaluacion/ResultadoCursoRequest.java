package fap.SistemaGestionEducativa.dto.request.evaluacion;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoCursoRequest {

    @NotNull(message = "El curso es obligatorio.")
    private Long idCurso;

    @NotNull(message = "El discente es obligatorio.")
    private Long idDiscente;
}

