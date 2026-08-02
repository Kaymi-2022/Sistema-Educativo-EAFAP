package fap.SistemaGestionEducativa.model.academico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "CATEGORIA")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categoria")
    @SequenceGenerator(name = "seq_categoria", sequenceName = "SEQ_CATEGORIA", allocationSize = 1)
    @Column(name = "ID_CATEGORIA")
    private Long idCategoria;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Curso> cursos;
}