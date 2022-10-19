package com.financialcontrol.crosscutting.configs

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .anyRequest().permitAll()

        /* add config to h2-console */
        http.headers().frameOptions().disable();
    }

}