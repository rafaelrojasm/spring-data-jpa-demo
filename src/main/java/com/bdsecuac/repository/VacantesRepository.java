package com.bdsecuac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdsecuac.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

	List<Vacante> findByEstatus(String estatus);
	List<Vacante> findBySalarioGreaterThan(Double salario);	
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
	List<Vacante> findTop5BySalarioBetweenOrderBySalarioDesc(double s1, double s2);
	List<Vacante> findByEstatusIn(String[] estatus);
}
