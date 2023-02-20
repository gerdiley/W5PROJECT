package it.epicode.GestioneDevices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import it.epicode.GestioneDevices.entities.Utente;

@Repository
public interface UtenteRepo extends JpaRepository<Utente, Integer> {
	@Query(
			nativeQuery = true,
			value = "SELECT * FROM utente WHERE utente.username = :u"
			)
	List<Utente> findByUsername(@Param("u") String username);
}
