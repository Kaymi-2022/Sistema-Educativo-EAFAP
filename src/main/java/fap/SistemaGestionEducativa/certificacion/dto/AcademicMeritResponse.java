package fap.SistemaGestionEducativa.certificacion.dto;

import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class AcademicMeritResponse {
    Long idCurso; String curso; String periodo; BigDecimal promedioMaximo; boolean empate;
    List<StudentMeritResponse> estudiantesPrimerPuesto;
}
