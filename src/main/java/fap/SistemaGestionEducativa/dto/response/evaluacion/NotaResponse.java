package fap.SistemaGestionEducativa.dto.response.evaluacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotaResponse {

    private Long idNota;

    private Long idEvaluacion;

    private String evaluacion;

    private Long idDiscente;

    private String discente;

    private BigDecimal calificacion;

    private String observacion;

    private String estado;

}