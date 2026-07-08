package fap.SistemaGestionEducativa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "AULA")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aula")
    @SequenceGenerator(name = "seq_aula", sequenceName = "SEQ_AULA", allocationSize = 1)
    @Column(name = "ID_AULA")
    private Long idAula;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "CAPACIDAD")
    @Positive
    private Integer capacidad;

    @Column(name = "UBICACION")
    private String ubicacion;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}