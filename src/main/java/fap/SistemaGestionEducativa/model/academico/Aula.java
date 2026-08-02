package fap.SistemaGestionEducativa.model.academico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "AULA")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aula")
    @SequenceGenerator(name = "seq_aula", sequenceName = "SEQ_AULA", allocationSize = 1)
    @Column(name = "ID_AULA")
    private Long idAula;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "CAPACIDAD")
    private Integer capacidad;

    @Column(name = "UBICACION")
    private String ubicacion;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}