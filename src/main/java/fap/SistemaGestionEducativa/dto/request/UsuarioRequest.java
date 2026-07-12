package fap.SistemaGestionEducativa.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioRequest {

    @NotBlank(message = "El DNI es obligatorio.")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe contener 8 dígitos.")
    private String dni;

    @NotBlank(message = "Los nombres son obligatorios.")
    @Size(max = 100)
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios.")
    @Size(max = 100)
    private String apellidos;

    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "Correo electrónico no válido.")
    private String email;

    @NotBlank(message = "El usuario es obligatorio.")
    @Size(min = 4, max = 50)
    private String username;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 6, max = 255)
    private String password;


}