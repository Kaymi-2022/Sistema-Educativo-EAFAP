package fap.SistemaGestionEducativa.dto.response.evaluacion;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionResponse {

    private Long idEvaluacion;

    private Long idCurso;

    private String curso;

    private String nombre;

    private String tipo;

    private BigDecimal peso;

    private LocalDate fecha;

    private String estado;

}
