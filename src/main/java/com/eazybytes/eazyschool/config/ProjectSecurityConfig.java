package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    /**
     * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
     * to move towards a component-based security configuration. It is recommended to create a bean
     * of type SecurityFilterChain for security related configurations.
     *
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/public/**").and()
                .authorizeHttpRequests()
                .antMatchers("/dashboard").authenticated()
                .antMatchers("/displayMessages/**").hasRole("ADMIN")
                .antMatchers("/closeMsg/**").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/displayProfile").authenticated()
                .antMatchers("/updateProfile").authenticated()
                .antMatchers("/student/**").hasRole("STUDENT")
                .antMatchers("/home").permitAll()
                .antMatchers("/holidays/**").permitAll()
                .antMatchers("/contact").permitAll()
                .antMatchers("/saveMsg").permitAll()
                .antMatchers("/courses").permitAll()
                .antMatchers("/about").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/public/**").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();

            return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
