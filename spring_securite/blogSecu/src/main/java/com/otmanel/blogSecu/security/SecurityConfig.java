package com.otmanel.blogSecu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// ici injection de dependance
	// cette annotation inqique a spring dinjecter un bean declarer ds le contextes
	@Autowired
	private DriverManagerDataSource dataSource;
	@Autowired
	private PasswordEncoder myPasswordEncoder;
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// pour un jdbc authentication, il y a 2 requetes minimales necessaire
		// 1- une permettant de recuperer un utilisateur a partir de son login
		// 		cette requete (userByUsernameQuery) doit renvoyer 3 champ: username, password, enabled (compte actif ou pas)
		// 2- authoritiesByUsernameQuery : requete permettant de recuperer les droits dun compte utilisateur( authorities ou role)
		
		/*
		 * Ppour gerer l'auhtentification, cad verifier lidentité d'une personne/acces, on configure 
		 * l'auhtentication manager
		 * Celui ci est fourni par spring security avc quelque implemenbtation deja prsente si on en a besoin
		 * 		-> in memory		   => en memoire uniqument pour des test
		 * 		-> JDBC				   => via des requte jdbc = a ses propre requetes que lon peux personaliser
		 * 								et fonctionne du moment que ces requetes renvoi les colonne attendues
		 * 		-> userDetailsService  => votre propre serive de recuperation dutilisateur authority
		 * 		-> autres 			   => pour les ldap
		 * 
		 * Au final cet authentication manager determine qui vous etes et vos droits mais pas si vous
		 * avez le droit d'acceder a une rssource, ceci est le role du http security
		 */		
		//VERSION JPA
		auth.userDetailsService(myUserDetailsService).passwordEncoder(myPasswordEncoder);
		
		/*version jdbc
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password, true as enabled from user where username = ?")
			.authoritiesByUsernameQuery("select username, rolename as authority from user inner join user_role "
					+ "on user.id = user_role.users_id "
					+ "inner join role on user_role.roles_id = role.id "
					+ "where user.username  = ?")
			.groupAuthoritiesByUsername("select username, 'none' as authority from user "
					+ "where 1=2 and username = ?")
			.passwordEncoder(myPasswordEncoder /*new MyPasswordEncoder());*/
		 // auth.ldapAuthentication().userSearchBase(userSearchBase)
		
		/*
		 * Ne pas stocker les mot de passee en claire,; il faut les hasher
		 * si on a la meme entrer, on a en sorti le meme hassh et donc spring security comparera la version hasher
		 * Dans la bdd seul les hash de mdp sont stoker mm si cette table est compromise, ns ne pouvons pas 
		 * simplement retourver le mot de passe originale
		 * 
		 * fonction de hash tres connu:
		 * 		- md5 ( vulnerable)
		 * 		- sha (1, 2, 256, 512) ok si pas trop critique
		 * 		- 
		 */
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * pour autoriser des acces aux url on utilise authorisze request sur lequel on peut declarer
		 * un ant matcher ( "url format ant apache") puis qui y a acces
		 * 	permitAll() 	==> tt le  monde
		 * 	anonymous 		==> les nom loguer
		 * 	authenticated	==> les logues
		 * 	hasRole 		==> avc un role precis
		 * 	hasAuthority 	==> quelqun qui a des droits specifique
		 * 	et autres varianrtes .... 
		 * 
		 * Attention, par defaut spring security assument, quand on demande la verification dun role(hasrole)
		 * que le nom du role ds la base est prfixé par "ROLE_USER .."
		 */
		
		
		http.authorizeRequests().antMatchers("/blog/**")
								.authenticated()
								.antMatchers("/bloginterne/**")
								.hasRole("USER")
								.antMatchers("/admin")
								.hasRole("ADMIN")
								.antMatchers("/index")
								.anonymous() // .permitAll() tt le monde y a acces
 		.and().formLogin()
		.and().httpBasic();
	}
	
}
