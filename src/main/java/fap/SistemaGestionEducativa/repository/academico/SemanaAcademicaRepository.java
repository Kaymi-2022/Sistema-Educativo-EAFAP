package fap.SistemaGestionEducativa.repository.academico;

import fap.SistemaGestionEducativa.model.academico.SemanaAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SemanaAcademicaRepository extends JpaRepository<SemanaAcademica, Long> {

    List<SemanaAcademica> findAllByEstado(String estado);

    boolean existsByNumeroSemanaAndEstado(Integer numeroSemana, String estado);

}
