package cabservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Lazy
	@Autowired
	private JwtAuthFilter jwtauthFilter;

	@Bean
	public UserDetailsService userdetails() {
		return new UserInfo();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(cs -> cs.disable())
				.authorizeHttpRequests(
						au -> (au.requestMatchers("/*/login","/customer/register","/driver/register","/").permitAll().requestMatchers("/**")).authenticated())
				.sessionManagement(ss -> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationprovider())
				.addFilterBefore(jwtauthFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationprovider() {
		DaoAuthenticationProvider authpro = new DaoAuthenticationProvider();
		authpro.setUserDetailsService(userdetails());
		authpro.setPasswordEncoder(passwordEncoder());
		return authpro;
	}

	@Bean
	public AuthenticationManager auth(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
