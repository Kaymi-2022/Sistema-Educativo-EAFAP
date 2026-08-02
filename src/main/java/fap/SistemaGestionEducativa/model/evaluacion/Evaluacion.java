package fap.SistemaGestionEducativa.model.evaluacion;

import fap.SistemaGestionEducativa.model.academico.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "EVALUACION")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_evaluacion")
    @SequenceGenerator(name = "seq_evaluacion", sequenceName = "SEQ_EVALUACION", allocationSize = 1)
    @Column(name = "ID_EVALUACION")
    private Long idEvaluacion;

    @ManyToOne
    @JoinColumn(name = "ID_CURSO", nullable = false)
    private Curso curso;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "PESO", nullable = false)
    private BigDecimal peso;

    @Column(name = "FECHA", nullable = false)
    private LocalDate fecha;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}