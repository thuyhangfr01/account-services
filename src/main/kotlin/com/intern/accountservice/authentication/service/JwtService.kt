package com.intern.accountservice.authentication.service

import com.intern.accountservice.authentication.entity.JwtRequest
import com.intern.accountservice.authentication.entity.JwtResponse
import com.intern.accountservice.authentication.entity.User
import com.intern.accountservice.authentication.exception.AccountException
import com.intern.accountservice.authentication.repository.UserRepository
import com.intern.accountservice.authentication.util.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import org.springframework.security.crypto.password.PasswordEncoder


@Service
class JwtService : UserDetailsService {
    @Autowired
    lateinit var jwtUtil: JwtUtil

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Throws(Exception::class)
    fun createJwtToken(jwtRequest: JwtRequest): JwtResponse? {
        val userName = jwtRequest.userName
        val userPassword = jwtRequest.userPassword
        authenticate(userName, userPassword)
        val userDetails = loadUserByUsername(userName)
        val newGeneratedToken = jwtUtil!!.generateToken(userDetails)
        val user: User = userRepository!!.findByUserName(userName!!)!!
        val status = HttpStatus.OK
        return JwtResponse(status, user, newGeneratedToken!!)

    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername (username: String?): UserDetails {
        val user: User = userRepository!!.findByUserName(username!!)!!
        return if (user != null)
        {
            org.springframework.security.core.userdetails.User(
                user.userName,
                user.password,
                getAuthority(user)
            )

        } else {
            throw UsernameNotFoundException("User not found with username: $username")
        }
    }

    private fun getAuthority(user: User): MutableCollection<out GrantedAuthority>? {
        val authorities: MutableSet<SimpleGrantedAuthority> = HashSet()
        user.role!!.forEach { role -> authorities.add(SimpleGrantedAuthority("ROLE_" + role!!.name)) }
        return authorities
    }

    @Throws(Exception::class)
    private fun authenticate(userName: String?, userPassword: String?) {
        try {
            authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(userName, userPassword))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        } catch (e: NotFoundException) {
            throw AccountException("User not found")
        }
    }
}