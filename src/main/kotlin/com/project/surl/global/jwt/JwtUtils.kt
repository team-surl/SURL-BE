package com.project.surl.global.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

object JwtUtils {

    fun generateToken(claim: JwtClaim, jwtProperties: JwtProperties): String {
        return JWT.create()
            .withIssuer(jwtProperties.issuer)
            .withSubject(jwtProperties.subject)
            .withIssuedAt(Date())
            .withExpiresAt(Date(Date().time + jwtProperties.expiration))
            .withClaim("accountId", claim.accountId)
            .withClaim("username", claim.username)
            .sign(Algorithm.HMAC256(jwtProperties.secret))
    }

    fun decodeToken(token: String, jwtProperties: JwtProperties) =
        JWT.require(Algorithm.HMAC256(jwtProperties.secret))
            .withIssuer(jwtProperties.issuer)
            .withSubject(jwtProperties.subject)
            .build()
            .verify(token)

}
