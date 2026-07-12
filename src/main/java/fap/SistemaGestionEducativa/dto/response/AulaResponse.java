package fap.SistemaGestionEducativa.dto.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AulaResponse {

    private Long idAula;

    private String nombre;

    private Integer capacidad;

    private String ubicacion;

    private String estado;
}
