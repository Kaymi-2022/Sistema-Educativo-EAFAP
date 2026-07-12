package fap.SistemaGestionEducativa.dto.response;

import fap.SistemaGestionEducativa.model.Curso;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class EvaluacionResponse {

    private Long idEvaluacion;

    private Curso curso;

    private String nombre;

    private String tipo;

    private BigDecimal peso;

    private LocalDate fecha;

    private String estado = "Y";
}
