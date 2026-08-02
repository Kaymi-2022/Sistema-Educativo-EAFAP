package fap.SistemaGestionEducativa.model.evaluacion;

import fap.SistemaGestionEducativa.model.academico.Curso;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "RESULTADO_CURSO")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResultadoCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_resultado")
    @SequenceGenerator(name = "seq_resultado", sequenceName = "SEQ_RESULTADO", allocationSize = 1)
    @Column(name = "ID_RESULTADO")
    private Long idResultado;

    @ManyToOne
    @JoinColumn(name = "ID_CURSO", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name="ID_USUARIO_ESTUDIANTE")
    private Usuario estudiante;

    @Column(name = "PROMEDIO_FINAL", nullable = false)
    private BigDecimal promedioFinal;

    @Column(name = "ESTADO_APROBACION", length = 1, nullable = false)
    private String estadoAprobacion;

    @Column(name = "FECHA_CIERRE", nullable = false)
    private LocalDate fechaCierre;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}
