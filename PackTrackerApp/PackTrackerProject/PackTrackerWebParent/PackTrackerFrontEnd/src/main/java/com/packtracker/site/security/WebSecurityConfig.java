package com.packtracker.site.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	// @Bean Annotation is applied on a method 
	// to specify that it returns a bean to be managed by Spring context.
	 
	@Bean
	public UserDetailsService userDetailsService() {
		return new PackTrackerUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService()); 
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
			
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
			http.authorizeRequests()
						.antMatchers("/user/register").permitAll()
						.antMatchers("/user/save").permitAll()
						.antMatchers("/user/check_email").permitAll()
						.anyRequest().authenticated()
						.and()
						.formLogin()
							.usernameParameter("email")
							.loginPage("/login")
							.permitAll()
						.and()
						.logout().permitAll();
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().permitAll();
//	}

	// exceptions (images, js files, webjars) to be displayed 
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**", "/js/** ", "/webjars/**");
	}
}
