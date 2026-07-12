package fap.SistemaGestionEducativa.dto.request;

import fap.SistemaGestionEducativa.model.Curso;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;


@Getter
@Setter
@Builder
public class EvaluacionRequest {


    @NotBlank(message = "El nombre de la evaluación no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El tipo de la evaluación no puede estar vacío")
    private String tipo;

    @NotNull(message = "El peso de la evaluación no puede ser nulo")
    private BigDecimal peso;

    @Pattern(regexp = "Y|N", message = "El estado de la evaluación debe ser 'Y' o 'N'")
    private String estado = "Y";
}
