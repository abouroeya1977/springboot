package ma.cigma.springsecurity.configuration;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
import ma.cigma.springsecurity.service.IMyActionService;
import ma.cigma.springsecurity.service.IUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IUserService userService;

	@Autowired
	private IMyActionService actionService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll().antMatchers("/welcome")
				.hasAnyAuthority("ADMIN", "CLIENT").antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/client/**").hasAuthority("CLIENT").anyRequest().authenticated().and().csrf().disable()
				.formLogin().loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/welcome")
				.usernameParameter("username").passwordParameter("password").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
				.exceptionHandling().accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	//
	// http.authorizeRequests().antMatchers("/").permitAll();
	// http.authorizeRequests().antMatchers("/login").permitAll();
	//
	// List<MyActionVo> list = actionService.getAll();
	//
	// for (MyActionVo vo : list) {
	// http.authorizeRequests().antMatchers(vo.getAction()).hasAnyAuthority(toTab(vo.getRoles()));
	//
	// }
	//
	// http.authorizeRequests().anyRequest().authenticated().and().csrf().disable().formLogin().loginPage("/login")
	// .failureUrl("/login?error=true").defaultSuccessUrl("/welcome").usernameParameter("username")
	// .passwordParameter("password").and().logout().logoutRequestMatcher(new
	// AntPathRequestMatcher("/logout"))
	// .logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/access-denied");
	// }
	//
	// private String[] toTab(List<RoleVo> roles) {
	// String[] tab = new String[roles.size()];
	// int i = 0;
	// for (RoleVo roleVo : roles) {
	// tab[i] = roleVo.getRole();
	// i++;
	// }
	// return tab;
	// }

	@PostConstruct
	public void init() {
		userService.cleanDataBase();
		userService.save(new RoleVo("ADMIN"));
		userService.save(new RoleVo("CLIENT"));

		RoleVo roleAdmin = userService.getRoleByName("ADMIN");
		RoleVo roleClient = userService.getRoleByName("CLIENT");

		UserVo admin1 = new UserVo("admin1", "admin1", Arrays.asList(roleAdmin));
		UserVo client1 = new UserVo("client1", "client1", Arrays.asList(roleClient));
		userService.save(admin1);
		userService.save(client1);

		MyActionVo adminAction = new MyActionVo("/ADMIN/**", Arrays.asList(roleAdmin));
		MyActionVo clientAction = new MyActionVo("/CLIENT/**", Arrays.asList(roleClient));
		actionService.save(adminAction);
		actionService.save(clientAction);
	}

}
