package it.epicode.GestioneDevices.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.GestioneDevices.entities.Dispositivo;
import it.epicode.GestioneDevices.entities.Utente;
import it.epicode.GestioneDevices.services.DispositivoSrv;
import it.epicode.GestioneDevices.services.UtenteSrv;

@RestController
@RequestMapping("/api")
public class AppController {
	
	@Autowired
	private UtenteSrv us;
	@Autowired
	private DispositivoSrv ds;
	
	//	------------GET PAGINA BENVENUTO------------
	
	@GetMapping
	public String hello() {
		return "hello world!";
	}
	
	//*******************************OPERAZIONI CRUD SU UTENTI******************************
	

	
	//	------------PAGINA LOGGED------------
	
	@PostMapping("/logged")
	public String logged() {
		return "logged";
	}
	
	
	//	------------GET UTENTI------------
	
	@GetMapping("/utenti")
   
    public List<Utente> getUtenti() {
        return us.getAll();
    }
	
	//	------------POST UTENTE------------
	
	@PostMapping("/utenti")
	
	public ResponseEntity<Object> saveUtente(@RequestBody Utente u) {
		Utente utente = us.save(u);
		
		return new ResponseEntity<Object>(utente, HttpStatus.CREATED);
	}
	
	//	------------GET UTENTE BY ID------------
	
	@GetMapping("/utenti/{id}")
	
	public Optional<Utente> getUtenteById(@PathVariable int id) {
		return us.getById(id);
	}
	
	//------------- PUT (MODIFICA USERNAME)--------
	
	@PutMapping("/utente/{id}")
	
	public ResponseEntity<Object> updateUsername(@PathVariable Integer id, @RequestBody Utente u){
		
		Optional<Utente> utenteObj = us.getById(id);
		Utente utente = utenteObj.get();
		
		utente.setUsername(u.getUsername());
		us.save(utente);
		
		return new ResponseEntity<Object>(utente, HttpStatus.OK);
	}
	
	//----------------- DELETE UTENTE ------------------------
	
	@DeleteMapping("/utente/{id}")
	
	public ResponseEntity<Object> delete(@PathVariable int id){
		Optional<Utente> utenteObj = us.getById(id);
		us.delete(utenteObj.get());
		
		return new ResponseEntity<>(String.format("Utente con id %d eliminato", id), HttpStatus.OK);
	}
	
	
	//*******************************OPERAZIONI CRUD SU DISPOSITIVI******************************
	
	//	------------GET DISPOSITIVI------------
	
	@GetMapping("/dispositivi")
	 public List<Dispositivo> getDispositivi(){
		return ds.getAll();
	}
	
	//	------------POST DISPOSITIVO----------------
	
	@PostMapping("/dispositivi")
	public ResponseEntity<Object> saveDispositivo(@RequestBody Dispositivo d){
		Dispositivo dispositivo = ds.save(d);
		return new ResponseEntity<Object>(dispositivo, HttpStatus.CREATED);
	}
	
	// -------------GET DISPOSITIVO BY ID----------
	
	@GetMapping("/dispositivi/{id}")
	public Optional<Dispositivo> getDispositivoById(@PathVariable int id) {
		return ds.getById(id);
	}
	
	// -------------PUT (MODIFICA STATO DISPOSITIVO)--------
	
	@PutMapping("/dispositivi/{id}")
	public ResponseEntity<Object> updateStato(@PathVariable int id, @RequestBody Dispositivo d){
		
		 Optional<Dispositivo> dispositivoObj = ds.getById(id);
		 
		 Dispositivo disp = dispositivoObj.get();
		 
		 disp.setStato(d.getStato());
		 
		 ds.save(disp);
		 
		 return new ResponseEntity<Object>(disp, HttpStatus.OK);
		
	}
	
	//----------------- DELETE DISPOSITIVO------------------------
	
	@DeleteMapping("/dispositivi/{id}")
	public ResponseEntity<Object> deleteDispositivo(@PathVariable int id){
		Optional<Dispositivo> dispObj  = ds.getById(id);
		
		ds.delete(dispObj.get());
		return new ResponseEntity<>(String.format("Hai eliminato il dispositivo con id %d ", id),HttpStatus.OK);
		 
	}
	
	
	
	
}
