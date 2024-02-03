package jfs.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jfs.backend.jwtToken.JwtAuthenticationEntryPoint;
import jfs.backend.jwtToken.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint point;
	@Autowired
	private JwtAuthenticationFilter filter;
	public static final String[] PUBLIC_URLS={"/v3/api-docs/**","/auth/**","/swagger-ui/**","/swagger-resources/**","/webjars/**"};
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.authorizeRequests()
				.requestMatchers(PUBLIC_URLS).permitAll ()
			//	.requestMatchers("/auth/register").permitAll()
				.requestMatchers (HttpMethod.GET).permitAll ()
				.requestMatchers (HttpMethod.DELETE).hasRole ("ADMIN")
				.requestMatchers ("/api/users/{userId}").hasAnyRole ("ADMIN","USER")
				.anyRequest ()
				.authenticated ()
				.and()
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();

	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//
//
//		http.csrf(csrf -> csrf.disable()).authorizeRequests().requestMatchers("/test").authenticated()
//				.requestMatchers("/auth/**").permitAll()
//				.requestMatchers("/auth/register").permitAll()
//			//	.requestMatchers(PUBLIC_URLS).permitAll()
//				.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
//				.requestMatchers("/api/users/getSingleUser/{userId}").hasAnyRole("ADMIN", "USER")
//				.requestMatchers("/api/users/getAllUser").hasRole("ADMIN")
//				.anyRequest().authenticated().and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//		return http.build();
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Step 4-- create authenticationManager in which we are configuring user
	// details
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

}