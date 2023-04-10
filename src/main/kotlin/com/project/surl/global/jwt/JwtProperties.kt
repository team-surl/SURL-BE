package com.project.surl.global.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val issuer: String,
    val subject: String,
    val expiration: Long,
    val secret: String,
)
