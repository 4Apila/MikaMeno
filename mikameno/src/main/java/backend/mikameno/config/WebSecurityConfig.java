package backend.mikameno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/").permitAll()
						.requestMatchers("/css/**").permitAll()
						.requestMatchers("/tapahtuma/*").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/tapahtumat").permitAll()
						.requestMatchers("/h2-console/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/tapahtuma/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/**").hasAnyAuthority("ADMIN", "USER")
						.requestMatchers(HttpMethod.PUT, "/api/**").hasAuthority("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("ADMIN")
						.anyRequest().authenticated())
				.formLogin(formlogin -> formlogin
						//.loginPage("/login") vain jos on oma login-sivu
						.defaultSuccessUrl("/", true)
						.permitAll())
				.httpBasic(Customizer.withDefaults())
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions
                                                .disable())) // h2-console
				.logout(logout -> logout
						.permitAll());

		return http.build();
	}
}