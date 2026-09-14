package fap.SistemaGestionEducativa.repository.academico;

import fap.SistemaGestionEducativa.model.academico.PeriodoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PeriodoAcademicoRepository extends JpaRepository<PeriodoAcademico, Long> {

    List<PeriodoAcademico> findAllByEstado(String estado);

    boolean existsByNombreIgnoreCaseAndEstado(String nombre, String estado);

}
