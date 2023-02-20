package it.epicode.GestioneDevices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.GestioneDevices.entities.Dispositivo;
import it.epicode.GestioneDevices.repositories.DispositivoRepo;

@Service
public class DispositivoSrv {
	
	@Autowired
	DispositivoRepo dr;
	
	public Dispositivo save(Dispositivo d) {
		return dr.save(d);
	}
	
	public List<Dispositivo> getAll(){
		return dr.findAll();
	}
	
	public Optional<Dispositivo> getById(int id) {
		return dr.findById(id);
	}
	
	public List<Dispositivo> getBytipo(String tipo){
		return dr.findByTipo(tipo);
	}
	public void delete(Dispositivo d) {
		dr.delete(d);
	}
}
