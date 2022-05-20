package com.salesianostriana.dam.AepApp.security;

import com.salesianostriana.dam.AepApp.security.jwt.JwtAccesDeniedHandler;
import com.salesianostriana.dam.AepApp.security.jwt.JwtAuthenticationEntryPoint;
import com.salesianostriana.dam.AepApp.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccesDeniedHandler jwtAccesDeniedHandler;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccesDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.GET, "/download/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .antMatchers(HttpMethod.GET, "/championships/**").permitAll()
                .antMatchers(HttpMethod.POST, "/championships/").permitAll()
                .antMatchers(HttpMethod.DELETE, "/championships/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/championships/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/judges/**").hasRole("ADMINSITRADOR")
                .antMatchers(HttpMethod.POST, "/judges/").permitAll()
                .antMatchers(HttpMethod.DELETE, "/judges/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/judges/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/clubs/**").permitAll()
                .antMatchers(HttpMethod.POST, "/clubs/").permitAll()
                .antMatchers(HttpMethod.DELETE, "/clubs/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/clubs/{id}").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
