package fap.SistemaGestionEducativa.repository.academico;

import fap.SistemaGestionEducativa.model.academico.Curso;
import fap.SistemaGestionEducativa.model.academico.PeriodoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    boolean existsByNombreIgnoreCaseAndPeriodoAcademicoAndEstado(
            String nombre,
            PeriodoAcademico periodoAcademico,
            String estado);

    List<Curso> findAllByEstado(String estado);
}
