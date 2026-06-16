package fap.SistemaGestionEducativa.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "EVALUACION")
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

    @Column(name = "PESO", nullable = false, precision = 5, scale = 2)
    private BigDecimal peso;

    @Column(name = "FECHA", nullable = false)
    private LocalDate fecha;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}