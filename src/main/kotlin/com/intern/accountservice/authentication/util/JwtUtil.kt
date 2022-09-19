package com.intern.accountservice.authentication.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function

@Component
class JwtUtil {
    private val SECRET_KEY = "learn_programming_yourself"

    private val TOKEN_VALIDITY = 3600 * 5

    fun getUsernameFromToken(token: String?): String {
        return getClaimFromToken(token) { obj: Claims -> obj.subject }
    }

    fun <T> getClaimFromToken(token: String?, claimsResolver: Function<Claims, T>): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    private fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).body
    }

    fun validateToken(token: String?, userDetails: UserDetails): Boolean? {
        val username = getUsernameFromToken(token)
        return username == userDetails.username && !isTokenExpired(token)!!
    }

    private fun isTokenExpired(token: String?): Boolean? {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun getExpirationDateFromToken(token: String?): Date {
        return getClaimFromToken(token) { obj: Claims -> obj.expiration }
    }

    fun generateToken(userDetails: UserDetails): String? {
        var claims: Map<String, Any> = HashMap()
        var token = Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact()
        return token
    }

}