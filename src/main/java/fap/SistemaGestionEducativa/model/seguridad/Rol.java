package fap.SistemaGestionEducativa.model.seguridad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "ROL")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rol")
    @SequenceGenerator(name = "seq_rol", sequenceName = "SEQ_ROL", allocationSize = 1)
    @Column(name = "ID_ROL")
    private Long idRol;

    @Column(name = "NOMBRE_ROL", length = 50, nullable = false)
    private String nombreRol;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioRol> usuarioRoles = new ArrayList<>();
}