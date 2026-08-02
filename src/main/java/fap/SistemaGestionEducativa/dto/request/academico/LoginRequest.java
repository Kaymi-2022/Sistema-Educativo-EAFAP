package fap.SistemaGestionEducativa.dto.request.academico;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Ingrese el usuario")
    private String username;

    @NotBlank(message = "Ingrese la contraseña")
    private String password;
}
