package com.otmanel.exo_struts_jpa_spring.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//indique a spring que c une classe de configuration donc chargement auto au demarrage contexte
// enablewebsec dit que cette classe va ns activé la securité web sinon il le comprend pas
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// authentification
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// pour definir les utuilisateur pour les tests
		auth.inMemoryAuthentication().withUser("otman").password("meknes").roles("USER")
			.and().withUser("toto").password("toto").roles("ADMIN");
	}
	
	// controle daccess
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/film/**").authenticated()
			.antMatchers("/films").hasRole("USER")
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/", "/restFilms").permitAll()
			.and().formLogin().and().httpBasic(); // a la fin on dit quon veut un login par formulaire et non securisé (pas de ssl)
	}
	
}
