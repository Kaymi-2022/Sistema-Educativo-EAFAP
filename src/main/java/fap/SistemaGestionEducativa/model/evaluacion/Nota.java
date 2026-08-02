package fap.SistemaGestionEducativa.model.evaluacion;

import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "NOTA")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="seq_nota")
    @SequenceGenerator(name = "seq_nota", sequenceName = "SEQ_NOTA", allocationSize = 1)
    @Column(name = "ID_NOTA")
    private Long idNota;

    @ManyToOne
    @JoinColumn(name = "ID_EVALUACION", nullable = false)
    private Evaluacion evaluacion;

    @ManyToOne
    @JoinColumn(name="ID_USUARIO_ESTUDIANTE")
    private Usuario estudiante;

    @Column(name = "CALIFICACION", nullable = false)
    @DecimalMin("0")
    @DecimalMax("20")
    private BigDecimal calificacion;

    @Column(name = "OBSERVACION")
    private String observacion;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";

}
