package fap.SistemaGestionEducativa.model.academico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="PERIODO_ACADEMICO")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PeriodoAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_periodo")
    @SequenceGenerator(
            name = "seq_periodo",
            sequenceName = "SEQ_PERIODO",
            allocationSize = 1
    )
    @Column(name = "ID_PERIODO")
    private Long idPeriodo;

    @Column(name = "NOMBRE", nullable = false, length = 20)
    private String nombre;

    @Column(name = "FECHA_INICIO", nullable = false)
    private java.util.Date fechaInicio;

    @Column(name = "FECHA_FIN", nullable = false)
    private java.util.Date fechaFin;

    @Column(name = "ESTADO", nullable = false, length = 1)
    private String estado = "Y";

    @OneToMany(mappedBy = "periodoAcademico", fetch = FetchType.LAZY)
    private List<Curso> cursos;

}