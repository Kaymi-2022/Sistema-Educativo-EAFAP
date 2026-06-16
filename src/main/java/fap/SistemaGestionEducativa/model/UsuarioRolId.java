package fap.SistemaGestionEducativa.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UsuarioRolId implements Serializable {

    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "ID_ROL")
    private Long idRol;
}