package fap.SistemaGestionEducativa.model.academico;

import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Table(name = "CURSO")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_curso")
    @SequenceGenerator(name = "seq_curso", sequenceName = "SEQ_CURSO", allocationSize = 1)
    @Column(name = "ID_CURSO")
    private Long idCurso;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORIA", nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_DOCENTE", nullable = false)
    private Usuario docente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERIODO", nullable = false)
    private PeriodoAcademico periodoAcademico;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Actividad> actividades;
}