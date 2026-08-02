package fap.SistemaGestionEducativa.dto.response.academico;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponse {

    private Long idCategoria;
    private String nombre;
    private String descripcion;
    private String estado;

}
