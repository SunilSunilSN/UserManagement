package com.roleManagment.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.roleManagment.Service.UserDataService;
import com.roleManagment.Service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/Registration", "/save", "AllUsers", "/UpdateRole",
				"/Javascript/**", "/css/**", "/images/**").permitAll().anyRequest().authenticated())
				.formLogin(login -> login.successHandler(customAuthenticationSuccessHandler()))
				.logout(logout -> logout.permitAll()).csrf(crsf -> crsf.disable())

		;
//		.exceptionHandling(eh -> eh.accessDeniedPage("/403"))
//		.requestMatchers("/")
//		.hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN").requestMatchers("/new")
//		.hasAnyAuthority("ADMIN", "CREATOR").requestMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
//		.requestMatchers("/delete/**").hasAuthority("ADMIN")
		return http.build();
	}

//	@Autowired
//	private UserDataService userDataService;
//	@Bean
//	public SimpleUrlAuthenticationSuccessHandler customAuthenticationSuccessHandler(HttpSecurity http) {
//		System.out.println(http);
//		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
//		handler.setDefaultTargetUrl("/user/" + userDataService.getCurrentUserId()); // Redirect here after successful login
//		return handler;
//	}
	@Autowired
	private UserDataService userDataService;

	@Bean
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
		// Use a placeholder URL; it will be replaced dynamically
		handler.setDefaultTargetUrl("/user/{userId}");
		return new CustomAuthenticationSuccessHandler(userDataService);
	}
}
