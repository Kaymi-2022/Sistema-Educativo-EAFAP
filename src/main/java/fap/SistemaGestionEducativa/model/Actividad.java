package fap.SistemaGestionEducativa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ACTIVIDAD")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_actividad")
    @SequenceGenerator(name = "seq_actividad", sequenceName = "SEQ_ACTIVIDAD", allocationSize = 1)
    @Column(name = "ID_ACTIVIDAD")
    private Long idActividad;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "TIPO")
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "ID_CURSO", nullable = false)
    private Curso curso;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}