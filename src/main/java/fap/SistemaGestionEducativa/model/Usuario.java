package fap.SistemaGestionEducativa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(
            name = "seq_usuario",
            sequenceName = "SEQ_USUARIO",
            allocationSize = 1
    )
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @NotBlank(message = "El DNI es obligatorio.")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe contener 8 dígitos.")
    @Column(name = "DNI")
    private String dni;

    @Column(name = "NOMBRES", nullable = false, length = 8)
    private String nombres;


    @Column(name = "APELLIDOS", nullable = false,length = 100)
    private String apellidos;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;


    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ESTADO", nullable = false, length = 1)
    private String estado = "Y";

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioRol> usuarioRoles = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioDocente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curso> cursos;

    @OneToMany(mappedBy = "usuarioEstudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CursoDiscente> cursosDiscente;

}