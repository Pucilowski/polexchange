package com.pucilowski.exchange.main.web;

import com.pucilowski.exchange.main.RootConfig;
import com.pucilowski.exchange.main.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by martin on 13/05/14.
 */

@Configuration
//@EnableAutoConfiguration
@EnableWebSecurity
//@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/api/user/registerAction").permitAll()
                .antMatchers("/api/user/**").authenticated()
                .anyRequest().permitAll();

        http
                .formLogin()
                        //.defaultSuccessUrl("/user")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        if (true) return;

        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/resources*//**").permitAll()
                .antMatchers("/dashboard").permitAll()
                .antMatchers("/sign-up").permitAll()
                .antMatchers("/api/contacts*//**").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/loginprocess")
                .failureUrl("/?loginFailure=true")
                .permitAll();

        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.authenticationProvider(customAuthenticationProvider());
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(RootConfig.passwordEncoder());

        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    }


    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}