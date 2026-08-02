package fap.SistemaGestionEducativa.dto.response.seguridad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolResponse {

    private Long idRol;
    private String combreRol;
    private String estado;
}
