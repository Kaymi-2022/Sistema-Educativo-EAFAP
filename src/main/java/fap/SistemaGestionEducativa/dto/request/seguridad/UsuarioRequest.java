package fap.SistemaGestionEducativa.dto.request.seguridad;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequest {

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
    private String dni;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100)
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 100)
    private String apellidos;

    @Email(message = "Correo inválido")
    private String email;

    @NotBlank(message = "El usuario es obligatorio")
    @Size(min = 4, max = 30)
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 100)
    private String password;


}