package fap.SistemaGestionEducativa.model;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "RESULTADO_CURSO")
public class ResultadoCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_resultado")
    @SequenceGenerator(name = "seq_resultado", sequenceName = "SEQ_RESULTADO", allocationSize = 1)
    @Column(name = "ID_RESULTADO")
    private Long idResultado;

    @ManyToOne
    @JoinColumn(name = "ID_CURSO", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "ID_DISCENTE", nullable = false)
    private Usuario discente;

    @Column(name = "PROMEDIO_FINAL", nullable = false)
    private Double promedioFinal;

    @Column(name = "ESTADO_APROBACION", length = 1, nullable = false)
    private String estadoAprobacion;

    @Column(name = "FECHA_CIERRE", nullable = false)
    private LocalDate fechaCierre;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}
