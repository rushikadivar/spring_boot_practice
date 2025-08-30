//package com.springbootpractice.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig {
//
//	@Autowired
//	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//	@Autowired
//	private UserDetailsService jwtUserDetailsService;
//
//	@Autowired
//	private JwtRequestFilter jwtRequestFilter;
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		// configure AuthenticationManager so that it knows from where to load
//		// user for matching credentials
//		// Use BCryptPasswordEncoder
//		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//}