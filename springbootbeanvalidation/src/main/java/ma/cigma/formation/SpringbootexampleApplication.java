package ma.cigma.formation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ma.cigma.formation.domaine.EmployeeVo;
import ma.cigma.formation.service.IService;

@SpringBootApplication
public class SpringbootexampleApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("h2")
	private IService service;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootexampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!service.getAll().isEmpty())
			return;
		service.save(new EmployeeVo(null, "Alami", "Mohamed", 12000d, "alamimhamed@france.fr"));
		service.save(new EmployeeVo(null, "Jamali", "Ali", 5000d, "jamaliali@ss.com"));
		service.save(new EmployeeVo(null, "Amrani", "Bilal", 6000d, "amranibilal@yahoo.fr"));
		service.save(new EmployeeVo(null, "Kadiri", "Samir", 13000d, "kadirisamir@hotmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));
		service.save(new EmployeeVo(null, "Choukri", "Abla", 12000d, "choukriabla@gmail.com"));

		List<EmployeeVo> employees = service.getAll();
		for (EmployeeVo employee : employees) {
			System.out.println(employee);
		}

	}

}
