package ma.cigma.springsecurity;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.cigma.springsecurity.domaine.EmpVo;
import ma.cigma.springsecurity.domaine.RoleVo;
import ma.cigma.springsecurity.domaine.UserVo;
import ma.cigma.springsecurity.service.IEmpService;
import ma.cigma.springsecurity.service.IUserService;

@SpringBootApplication
public class LoginCRUDApplication implements CommandLineRunner {

	@Autowired
	private IUserService userService;
	@Autowired
	private IEmpService empService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LoginCRUDApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		userService.cleanDataBase();
		userService.save(new RoleVo("ADMIN"));
		userService.save(new RoleVo("CLIENT"));
		userService.save(new RoleVo("SUPER_ADMIN"));

		RoleVo roleAdmin = userService.getRoleByName("ADMIN");
		RoleVo roleClient = userService.getRoleByName("CLIENT");
		RoleVo roleSuperAdmin = userService.getRoleByName("SUPER_ADMIN");
		
		UserVo admin1 = new UserVo("admin1", bCryptPasswordEncoder.encode("admin1"), Arrays.asList(roleAdmin,roleClient));
		UserVo admin2 = new UserVo("admin2", bCryptPasswordEncoder.encode("admin2"), Arrays.asList(roleAdmin));
		UserVo client1 = new UserVo("client1", bCryptPasswordEncoder.encode("client1"), Arrays.asList(roleClient));
		UserVo superadmin = new UserVo("abbou", bCryptPasswordEncoder.encode("abbou"), Arrays.asList(roleSuperAdmin,roleAdmin,roleClient));
		
		userService.save(admin1);
		userService.save(client1);
		userService.save(admin2);
		userService.save(superadmin);

		// *************
		empService.save(new EmpVo("emp1", 10000d, "Fonction1"));
		empService.save(new EmpVo("emp2", 20000d, "Fonction3"));
		empService.save(new EmpVo("emp3", 30000d, "Fonction4"));
		empService.save(new EmpVo("emp4", 40000d, "Fonction5"));
		empService.save(new EmpVo("emp5", 50000d, "Fonction6"));

	}

}
