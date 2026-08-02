package fap.SistemaGestionEducativa.dto.response.dashboard;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionPendienteResponse {

    private Long idEvaluacion;

    private String evaluacion;

    private String curso;

    private LocalDate fecha;

    private String docente;

}
