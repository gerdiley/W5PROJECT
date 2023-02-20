package it.epicode.GestioneDevices;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.epicode.GestioneDevices.config.Beans;
import it.epicode.GestioneDevices.entities.Dispositivo;
import it.epicode.GestioneDevices.entities.Role;
import it.epicode.GestioneDevices.entities.RoleType;
import it.epicode.GestioneDevices.entities.TipoDispositivo;
import it.epicode.GestioneDevices.entities.Utente;
import it.epicode.GestioneDevices.services.DispositivoSrv;
import it.epicode.GestioneDevices.services.RoleSrv;
import it.epicode.GestioneDevices.services.UtenteSrv;


@SpringBootApplication
public class GestioneDevicesApplication implements CommandLineRunner {

	@Autowired
	private UtenteSrv us;
	@Autowired
	private DispositivoSrv ds;
	@Autowired
	private RoleSrv rs;
	
	
	public static void main(String[] args) {
		SpringApplication.run(GestioneDevicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		boolean populate = false;
		
		if (populate) {
			populatedb();
		}
		
//		getRolesFromUserById(1);
		
	}
	
	private void getRolesFromUserById(int id) {
		Optional<Utente> authUserObj = us.getById(id);
		Utente authUser = authUserObj.get();
		String role = "USER";
		Set<Role> roles = authUser.getRoles();
		
		for( Role r : roles ) {
			if( r.getType().toString().contains("ADMIN") ) {
				role = "ADMIN";
				break;
			}
		}
		
		System.out.println(role);
	}
	
	private void populatedb() {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		Utente u1 = (Utente)ctx.getBean("utente", "gerdiley", "Gerardo Di Letizia", "gerardo@gmail.com", "test");
		
		
		Role r1 = (Role)ctx.getBean("role", RoleType.ROLE_ADMIN);
		Role r2 = (Role)ctx.getBean("role", RoleType.ROLE_USER);
		
		u1.setRoles(new HashSet<>() {{
			add(r1);
		}});
		
		rs.saveRole(r1);
		rs.saveRole(r2);
		
		
		us.save(u1);
		
		Dispositivo d1 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.SMARTPHONE, u1);
		
		
		ds.save(d1);
		
		((AnnotationConfigApplicationContext)ctx).close();
	}

}
