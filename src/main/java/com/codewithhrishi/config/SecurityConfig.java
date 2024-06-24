package com.codewithhrishi.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.codewithhrishi.security.EntryPoint;
import com.codewithhrishi.security.JwtFiltetr;


import jakarta.servlet.http.HttpServletRequest;
@Configuration
@EnableWebMvc
public class SecurityConfig {

	public static final String[] URL_CONST_FOR_OWNER_ADMIN= {"/api/admin/**"
			};
	public static final String[] OPEN_REQUEST= {"/auth/**"
	};
    @Autowired
    private EntryPoint point;
    @Autowired
    private JwtFiltetr filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).cors(cors->cors.configurationSource(getCorsConfigurationSource()))
           .authorizeHttpRequests(auth->auth.requestMatchers(OPEN_REQUEST).permitAll().	   
        		   requestMatchers(URL_CONST_FOR_OWNER_ADMIN).hasAnyRole("RESTAURANT_OWNER","ADMIN").requestMatchers("/api/**").authenticated().anyRequest().authenticated()).
               exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

	/*
	 * @Bean
	 * 
	 * @Order(Ordered.HIGHEST_PRECEDENCE) public FilterRegistrationBean<CorsFilter>
	 * corsFilter() { UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); CorsConfiguration config = new
	 * CorsConfiguration();
	 * 
	 * config.setAllowCredentials(true); config.addAllowedOriginPattern("*"); // Use
	 * addAllowedOriginPattern instead of addAllowedOrigin for more flexibility
	 * config.addAllowedHeader("*"); // Allow all headers
	 * config.addAllowedMethod("*"); // Allow all HTTP methods
	 * config.setMaxAge(3600L); // Cache the response for 1 hour
	 * 
	 * // Expose headers config.addExposedHeader("Content-Type");
	 * config.addExposedHeader("Authorization"); // Add more headers as needed
	 * 
	 * source.registerCorsConfiguration("/**", config);
	 * 
	 * FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new
	 * CorsFilter(source)); bean.setOrder(Ordered.HIGHEST_PRECEDENCE); // Ensure
	 * this filter is applied early in the filter chain return bean; }
	 */
    @Bean
    public CorsConfigurationSource getCorsConfigurationSource() {
    	return new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

				CorsConfiguration config = new CorsConfiguration();
 
  config.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://localhost:3000"));
  config.setAllowedMethods(Collections.singletonList("*"));
  config.setAllowCredentials(true);
  config.setAllowedHeaders(Collections.singletonList("*"));
  config.setExposedHeaders(Arrays.asList("Authorization"));
  config.setMaxAge(3600L);
				return config;
			}
		};
    }
    
}