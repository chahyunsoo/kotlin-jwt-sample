package sample.demo.kotlin_jwt.common.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import sample.demo.kotlin_jwt.common.exception.InvalidTokenException
import java.util.Date

const val EXPIRED_MILLI_SECONDS = 1000 * 60 * 30

@Component
class JwtTokenProvider {
    @Value("\${jwt.secret-key}")
    lateinit var secretKey: String

    private val key by lazy { Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)) }

    fun generateToken(authentication: Authentication): TokenInfo {
        val authorities: String = authentication
            .authorities
            .joinToString(",", transform = GrantedAuthority::getAuthority)
        println("authorities = ${authorities}")

        val now = Date()
        val accessExpiration = Date(now.time + EXPIRED_MILLI_SECONDS)

        val accessToken = Jwts.builder()
            .setSubject(authentication.name)
            .claim("auth", authorities)
            .setIssuedAt(now)
            .setExpiration(accessExpiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        return TokenInfo("Bearer", accessToken)
    }

    fun getAuthentication(accessToken: String): Authentication {
        val claimsInfo = getClaims(accessToken)
        val auth = claimsInfo["auth"] ?: throw InvalidTokenException("잘못된 토큰입니다.")


        val authorities: Collection<GrantedAuthority> = (auth as String)
            .split(",")
            .map { SimpleGrantedAuthority(it) }
        println("authorities = ${authorities}")

        val principal: UserDetails = User(claimsInfo.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    fun getClaims(accessToken: String) : Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJwt(accessToken)
            .body
    }

    fun validateToken(token: String): Boolean {
        try {
            getClaims(token)
            return true
        } catch (e: Exception) {
            when (e) {
                is SecurityException -> {}
                is MalformedJwtException -> {}
                is ExpiredJwtException -> {}
                is UnsupportedJwtException -> {}
                is IllegalArgumentException -> {}
                else -> {}  // else
            }
            println(e.message)
        }
        return false
    }
}