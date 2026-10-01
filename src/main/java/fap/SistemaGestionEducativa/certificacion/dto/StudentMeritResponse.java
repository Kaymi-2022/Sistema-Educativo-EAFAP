package fap.SistemaGestionEducativa.certificacion.dto;

import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;

@Value
@Builder
public class StudentMeritResponse {
    Long idDiscente; String estudiante; String dni; BigDecimal promedioFinal;
}
