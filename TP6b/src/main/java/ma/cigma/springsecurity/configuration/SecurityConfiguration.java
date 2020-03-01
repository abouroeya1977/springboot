package ma.cigma.springsecurity.configuration;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ma.cigma.springsecurity.domaine.MyActionVo;
import ma.cigma.springsecurity.domaine.RoleVo;
import ma.cigma.springsecurity.domaine.UserVo;
import ma.cigma.springsecurity.service.IUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IUserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll();
		List<MyActionVo> actions = userService.getAllActions();
		for (MyActionVo myActionVo : actions) {
			List<RoleVo> roles = myActionVo.getRoles();
			http.authorizeRequests().antMatchers(myActionVo.getAction()).hasAnyAuthority(getRoles(roles));
		}
		http.authorizeRequests().anyRequest().authenticated().and().csrf().disable().formLogin().loginPage("/login")
				.failureUrl("/login?error=true").defaultSuccessUrl("/welcome").usernameParameter("username")
				.passwordParameter("password").and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/access-denied");
	}

	@PostConstruct
	public void init() {
		userService.cleanDataBase();
		userService.save(new RoleVo("ADMIN"));
		userService.save(new RoleVo("CLIENT"));
		userService.save(new RoleVo("SUPER_ADMIN"));

		RoleVo roleAdmin = userService.getRoleByName("ADMIN");
		RoleVo roleClient = userService.getRoleByName("CLIENT");
		RoleVo roleSuperAdmin = userService.getRoleByName("SUPER_ADMIN");

		UserVo admin1 = new UserVo("admin1", "admin1", Arrays.asList(roleAdmin));
		UserVo client1 = new UserVo("client1", "client1", Arrays.asList(roleClient));
		UserVo superadmin1 = new UserVo("superadmin1", "superadmin1", Arrays.asList(roleSuperAdmin));
		UserVo toto = new UserVo("toto", "toto", Arrays.asList(roleAdmin, roleClient));

		userService.save(admin1);
		userService.save(client1);
		userService.save(superadmin1);
		userService.save(toto);

		MyActionVo admin = new MyActionVo("/admin/**", Arrays.asList(roleAdmin));
		MyActionVo client = new MyActionVo("/client/**", Arrays.asList(roleClient));

		userService.save(admin);
		userService.save(client);
	}

	private String[] getRoles(List<RoleVo> roles) {

		String[] tab = new String[roles.size()];
		int i = 0;
		for (RoleVo roleVo : roles) {
			tab[i] = roleVo.getRole();
		}

		return tab;
	}

	@Override
	public void configure(WebSecurity web){
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
