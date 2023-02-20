package it.epicode.GestioneDevices.config;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.epicode.GestioneDevices.entities.Role;
import it.epicode.GestioneDevices.entities.Utente;
import it.epicode.GestioneDevices.services.UtenteSrv;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UtenteSrv us;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/api")
			.permitAll()
		.anyRequest()
			.authenticated()
		.and()
		.formLogin()
			.successForwardUrl("/logged")
		.and()
		.logout()
		.and()
		.csrf()
			.disable();
	};
	
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		Optional<Utente> authUserObj = us.getById(1);
		Utente authUser = authUserObj.get();
		
		String role = "USER";
		
		Set<Role> roles = authUser.getRoles();
		
		for(Role r: roles) {
			if(r.getType().toString().contains("ADMIN")){
				role = "ADMIN";
				break;
			}
		}
		auth.inMemoryAuthentication()
		.withUser(authUser.getUsername())
		.password(passwordEncoder().encode(authUser.getPassword()))
		.roles(role);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}

