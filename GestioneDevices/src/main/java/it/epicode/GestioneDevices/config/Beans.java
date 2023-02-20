package it.epicode.GestioneDevices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.epicode.GestioneDevices.entities.Dispositivo;
import it.epicode.GestioneDevices.entities.Role;
import it.epicode.GestioneDevices.entities.RoleType;
import it.epicode.GestioneDevices.entities.StatoDispositivo;
import it.epicode.GestioneDevices.entities.TipoDispositivo;
import it.epicode.GestioneDevices.entities.Utente;

@Configuration
public class Beans {
	
	@Bean
	@Scope("prototype")
	public Utente utente(String username, String fullname, String email, String password) {
		return Utente.builder()
				.fullname(fullname)
				.username(username)
				.email(email)
				.password(password)
				.build();
	};
	
	@Bean
	@Scope("prototype")
	public Dispositivo dispositivo(TipoDispositivo tipo, Utente u) {
		return Dispositivo.builder()
				.tipo(tipo)
				.id_utente(u)
				.stato(StatoDispositivo.ASSEGNATO)
				.build();
	}
	
	@Bean
	@Scope("prototype")
	public Role role(RoleType rt) {
		return Role.builder()
				.type(rt)
				.build();
	}
	
	
}
