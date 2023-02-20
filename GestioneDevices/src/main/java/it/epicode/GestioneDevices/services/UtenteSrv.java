package it.epicode.GestioneDevices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.GestioneDevices.entities.Dispositivo;
import it.epicode.GestioneDevices.entities.Utente;
import it.epicode.GestioneDevices.repositories.DispositivoRepo;
import it.epicode.GestioneDevices.repositories.UtenteRepo;

@Service
public class UtenteSrv {
	
	@Autowired
	UtenteRepo ur;
	
	public Utente save(Utente d) {
			return ur.save(d);	
	}
	
	public List<Utente> getAll(){
		return ur.findAll();
	}
	
	public Optional<Utente> getById(int id) {
		return ur.findById(id);
	}
	public List<Utente> getByUsername(String username) {
		return ur.findByUsername(username);
	}
	
	public void delete(Utente u) {
		ur.delete(u);
	}
}
