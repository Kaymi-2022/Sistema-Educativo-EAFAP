package fap.SistemaGestionEducativa.repository.academico;

import fap.SistemaGestionEducativa.model.academico.PeriodoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodoAcademicoRepository extends JpaRepository<PeriodoAcademico, Long> {

    List<PeriodoAcademico> findAllByEstado(String estado);

    boolean existsByNombreIgnoreCaseAndEstado(String nombre, String estado);

}
