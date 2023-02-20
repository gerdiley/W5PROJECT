package it.epicode.GestioneDevices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.GestioneDevices.entities.Role;
import it.epicode.GestioneDevices.repositories.RoleRepo;

@Service
public class RoleSrv {
	@Autowired
	RoleRepo rp;
	
	public Role saveRole(Role r) {
		return rp.save(r);
	}
}
