package fap.SistemaGestionEducativa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ROL")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rol")
    @SequenceGenerator(name = "seq_rol", sequenceName = "SEQ_ROL", allocationSize = 1)
    @Column(name = "ID_ROL")
    private Long idRol;

    @Column(name = "NOMBRE_ROL", nullable = false)
    private String nombreRol;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}