package fap.SistemaGestionEducativa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="PERIODO_ACADEMICO")
public class PeriodoAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_periodo")
    @SequenceGenerator(
            name = "seq_periodo",
            sequenceName = "SEQ_PERIODO",
            allocationSize = 1
    )
    @Column(name = "ID_PERIODO")
    private Long idPeriodo;

    @Column(name = "NOMBRE", nullable = false, length = 20)
    private String nombre;

    @Column(name = "FECHA_INICIO", nullable = false)
    private java.util.Date fechaInicio;

    @Column(name = "FECHA_FIN", nullable = false)
    private java.util.Date fechaFin;

    @Column(name = "ESTADO", nullable = false, length = 1)
    private String estado = "Y";

    @OneToMany(mappedBy = "periodoAcademico", fetch = FetchType.LAZY)
    private List<Curso> cursos;

}