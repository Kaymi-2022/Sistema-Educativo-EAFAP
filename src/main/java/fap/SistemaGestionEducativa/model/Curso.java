package fap.SistemaGestionEducativa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CURSO")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_curso")
    @SequenceGenerator(name = "seq_curso", sequenceName = "SEQ_CURSO", allocationSize = 1)
    @Column(name = "ID_CURSO")
    private Long idCurso;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "ID_DOCENTE", nullable = false)
    private Usuario docente;

    @Column(name = "PERIODO", nullable = false)
    private String periodo;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}