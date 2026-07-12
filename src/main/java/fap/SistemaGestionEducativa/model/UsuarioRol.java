package fap.SistemaGestionEducativa.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "USUARIO_ROL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRol{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario_rol")
    @SequenceGenerator(name = "seq_usuario_rol", sequenceName = "SEQ_USUARIO_ROL", allocationSize = 1)
    @Column(name = "ID_USUARIO_ROL")
    private Long idUsuarioRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ROL", nullable = false)
    private Rol rol;

    @Column(name = "FECHA_ASIGNACION", nullable = false)
    private LocalDate fechaAsignacion;

    @Column(name= "ESTADO")
    private String estado = "Y";


}