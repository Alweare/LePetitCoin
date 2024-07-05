package fr.eni.enchere.configurationSecurity;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class EnchereSecurityConfig {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	//private String SELECT_UTILISATEUR ="SELECT pseudo, mot_de_passe FROM UTILISATEURS WHERE pseudo=?";
	private String SELECT_UTILISATEUR ="SELECT pseudo, mot_de_passe, 1 as enabled FROM UTILISATEURS WHERE pseudo = ?";
	private String SELECT_ROLES="SELECT pseudo,administrateur FROM UTILISATEURS where pseudo=?";


	
	
	@Bean
   PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	

	@Bean
	UserDetailsManager userDetailManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.setUsersByUsernameQuery(SELECT_UTILISATEUR);
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);
		return jdbcUserDetailsManager;
	}
	

	@Bean
	SecurityFilterChain filtreChaine(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize-> authorize 
			
				.requestMatchers("/connexion").permitAll()
				.requestMatchers("/").permitAll()
				.requestMatchers("/session").permitAll()
				.requestMatchers("/creationProfil").permitAll()
				.requestMatchers("/creationVente").permitAll()
				.requestMatchers("/listeEnchere").permitAll()
				.requestMatchers("/monProfil").permitAll()
				.requestMatchers("/css/*").permitAll()
				.requestMatchers("/images/*").permitAll()
				.requestMatchers("/creationProfil").permitAll()
				.requestMatchers("/nouveauMDP").permitAll()
				.anyRequest().permitAll());

		http.formLogin(form->{
			form.loginPage("/connexion").permitAll();
			form.defaultSuccessUrl("/").permitAll();//Changer plus tard au cas ou 
		});
		
		http.logout(logout->
			logout
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
				.logoutSuccessUrl("/").permitAll());
		
		return http.build();
	}

	
}

