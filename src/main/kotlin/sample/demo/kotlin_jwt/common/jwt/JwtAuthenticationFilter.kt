package sample.demo.kotlin_jwt.common.jwt

import ch.qos.logback.core.util.StringUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean

class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val resolvedToken = resolveToken(request as HttpServletRequest)
        if (resolvedToken != null && jwtTokenProvider.validateToken(resolvedToken)) {
            val authentication = jwtTokenProvider.getAuthentication(resolvedToken)
            println("authentication = ${authentication}")
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain?.doFilter(request, response)
    }

    private fun resolveToken(httpServletRequest: HttpServletRequest): String? {
        val bearerToken = httpServletRequest.getHeader("Authorization")

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            bearerToken.substring(7)
        } else {
            null
        }
    }
}