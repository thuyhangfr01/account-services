//package com.intern.accountservice.authentication.config
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.http.HttpHeaders
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {
//    @Autowired
//    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint? = null
//
//    @Autowired
//    private val jwtRequestFilter: JwtRequestFilter? = null
//
//    @Autowired
//    private val jwtService: UserDetailsService? = null
//
//    @Bean
//    @Throws(Exception::class)
//    override fun authenticationManagerBean(): AuthenticationManager {
//        return super.authenticationManagerBean()
//    }
//
//    @Throws(Exception::class)
//    override fun configure(httpSecurity: HttpSecurity) {
//        httpSecurity.cors()
//        httpSecurity.csrf().disable()
//            .authorizeRequests().antMatchers("/authenticate", "/registerNewUser").permitAll()
//            .antMatchers(HttpHeaders.ALLOW).permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
//            .and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
//    }
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//    }
//
//    @Autowired
//    @Throws(Exception::class)
//    fun configureGlobal(authenticationManagerBuilder: AuthenticationManagerBuilder) {
//        authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder())
//    }
//}
