package fap.SistemaGestionEducativa.dto.response.academico;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AulaResponse {

    private Long idAula;

    private String nombre;

    private Integer capacidad;

    private String ubicacion;

    private String estado;

}