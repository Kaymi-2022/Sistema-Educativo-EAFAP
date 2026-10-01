package fap.SistemaGestionEducativa.certificacion.dto;

import fap.SistemaGestionEducativa.dto.response.evaluacion.NotaResponse;
import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class AcademicRecordResponse {
    Long idDiscente; String estudiante; String dni; Long idCurso; String curso; String periodo;
    LocalDate fechaInicio; LocalDate fechaTermino; List<NotaResponse> evaluaciones;
    BigDecimal promedioFinal; String estadoFinal;
}
