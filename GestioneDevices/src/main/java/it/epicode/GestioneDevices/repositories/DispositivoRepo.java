package it.epicode.GestioneDevices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.epicode.GestioneDevices.entities.Dispositivo;

@Repository
public interface DispositivoRepo extends JpaRepository<Dispositivo, Integer> {

	
	@Query(
			nativeQuery = true,
			value = "SELECT * FROM dispositivo WHERE dispositivo.tipo = :t"
			)
	List<Dispositivo> findByTipo(@Param("t") String tipo ); 


}
