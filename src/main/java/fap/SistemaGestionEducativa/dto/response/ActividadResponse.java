package fap.SistemaGestionEducativa.dto.response;

import fap.SistemaGestionEducativa.model.Curso;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ActividadResponse {

    private Long idActividad;

    private String nombre;

    private String tipo;

    private Curso curso;

    private String estado;
}
