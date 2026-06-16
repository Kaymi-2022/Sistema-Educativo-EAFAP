package fap.SistemaGestionEducativa.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CURSO_DISCENTE")
public class CursoDiscente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_curso_discente")
    @SequenceGenerator(name = "seq_curso_discente", sequenceName = "SEQ_CURSO_DISCENTE", allocationSize = 1)
    @Column(name = "ID_CURSO_DISCENTE")
    private Long idCursoDiscente;

    @ManyToOne
    @JoinColumn(name = "ID_CURSO", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "ID_DISCENTE", nullable = false)
    private Usuario discente;

    @Column(name = "FECHA_MATRICULA")
    private LocalDate fechaMatricula;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}