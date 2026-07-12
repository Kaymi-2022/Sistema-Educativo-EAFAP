package fap.SistemaGestionEducativa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AulaRequest {

    @NotBlank(message = "El nombre del aula no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La capacidad del aula no puede estar vacía")
    private Integer capacidad;

    @NotBlank(message = "La ubicación del aula no puede estar vacía")
    private String ubicacion;

    @Pattern(regexp = "Y|N", message = "El estado debe ser Y o N.")
    private String estado = "Y";
}
