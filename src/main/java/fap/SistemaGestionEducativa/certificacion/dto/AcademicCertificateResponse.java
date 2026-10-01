package fap.SistemaGestionEducativa.certificacion.dto;

import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class AcademicCertificateResponse {
    Long idDiscente; String estudiante; String dni; Long idCurso; String curso; String periodo;
    LocalDate fechaInicio; LocalDate fechaTermino; BigDecimal promedioFinal; String estado; LocalDate fechaEmision;
}
