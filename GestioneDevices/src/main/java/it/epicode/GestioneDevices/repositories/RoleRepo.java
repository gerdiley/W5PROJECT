package it.epicode.GestioneDevices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.GestioneDevices.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
