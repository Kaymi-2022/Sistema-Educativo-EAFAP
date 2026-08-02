package fap.SistemaGestionEducativa.model.seguridad;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "USUARIO_ROL")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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