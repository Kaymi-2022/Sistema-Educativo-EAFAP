package fap.SistemaGestionEducativa.dto.response.reporte;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotaDetalleResponse {

    private Long idEvaluacion;

    private String evaluacion;

    private String tipo;

    private int peso;

    private LocalDate fecha;

    private BigDecimal nota;

    private String observacion;

}