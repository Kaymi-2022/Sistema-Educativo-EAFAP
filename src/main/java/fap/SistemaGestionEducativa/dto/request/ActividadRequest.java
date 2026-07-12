package fap.SistemaGestionEducativa.dto.request;

import fap.SistemaGestionEducativa.model.Curso;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ActividadRequest {

    @NotBlank(message = "El nombre de la actividad no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El tipo de la actividad no puede estar vacío")
    private String tipo;

    @NotBlank(message = "El curso de la actividad no puede estar vacío")
    private Curso curso;

}
