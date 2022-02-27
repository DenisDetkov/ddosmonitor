package com.ddos.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("#")
                .password("#")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("#")
                .password("#")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
