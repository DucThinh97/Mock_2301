package com.vti.security;

import com.google.common.collect.ImmutableList;
import com.vti.security.jwt.AuthEntryPointJwt;
import com.vti.security.jwt.AuthTokenFilter;
import com.vti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserService userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/api/v1/catalogs/**").anonymous()
                .antMatchers("/api/v1/products/**").anonymous()
                .antMatchers("/api/v1/auth/signup").anonymous()
                .antMatchers("/api/v1/auth/signin").anonymous()
                .antMatchers("/api/v1/users/activeUser").anonymous()
                .antMatchers("/api/v1/users/userName/**").anonymous()
                .antMatchers("/api/v1/users/email/**").anonymous()
                .antMatchers("/api/v1/auth/signout").hasAnyAuthority("USER","STAFF","ADMIN")
                .antMatchers("/api/v1/users/profile").hasAuthority("USER")
                .antMatchers("/api/v1/users/fullProfile").hasAuthority("USER")
                .antMatchers("/api/v1/users/paymentProfile").hasAuthority("USER")
                .antMatchers("/api/v1/carts/**").hasAuthority("USER")

                .antMatchers("/api/v1/comments/productId").anonymous()
                .antMatchers("/api/v1/comments").hasAuthority("USER")

                .antMatchers("/api/v1/comments/**").anonymous()

                .antMatchers("/api/v1/oderLists/**").hasAuthority("USER")
                .antMatchers("/api/v1/oderDetails/**").hasAuthority("USER")





                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .cors()
                .and()
                .csrf().disable()
        ;

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
