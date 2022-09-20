package com.intern.accountservice.authentication.config

import com.intern.accountservice.authentication.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Suppress("DEPRECATION")
@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint

    @Autowired
    lateinit var jwtRequestFilter: JwtRequestFilter

    @Autowired
    lateinit var jwtService: JwtService

    @Bean(name = [BeanIds.AUTHENTICATION_MANAGER])
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager? {
        return super.authenticationManagerBean()
    }

    @Throws(java.lang.Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.cors()
        httpSecurity.csrf().disable()
            .authorizeRequests().antMatchers("/api/authenticate", "/api/registerNewAdmin", "/api/registerNewStudent","/api/registerNewTeacher").permitAll()
            .antMatchers(HttpHeaders.ALLOW).permitAll()
            .antMatchers("/v3/api-docs/**", "/configuration/ui", "/swagger-resources",
                    "/configuration/security", "/swagger-ui.html", "/webjars/**",
                    "/swagger-resources/configuration/ui","/swagger-ui/index.html",
                    "/swagger-ui/**").permitAll()
            .antMatchers("/api/forStudent").hasRole("STUDENT")
            .antMatchers("/api/forTeacher").hasRole("TEACHER")
            .antMatchers("/api/forAdmin").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
//            .antMatchers("/swagger-ui/index.html#/").permitAll()
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Autowired
    @Throws(java.lang.Exception::class)
    fun configureGlobal(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder.userDetailsService<UserDetailsService>(jwtService)
            .passwordEncoder(passwordEncoder())
    }
}