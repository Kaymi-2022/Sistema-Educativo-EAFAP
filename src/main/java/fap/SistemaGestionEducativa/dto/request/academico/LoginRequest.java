package fap.SistemaGestionEducativa.dto.request.academico;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Ingrese el usuario")
    private String username;

    @NotBlank(message = "Ingrese la contraseña")
    private String password;
}
