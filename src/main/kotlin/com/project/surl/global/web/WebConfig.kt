package com.project.surl.global.web

import org.springframework.stereotype.Component
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Component
class WebConfig : WebFluxConfigurer {
    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry.addMapping("/**")
            .allowedMethods("*")
            .allowCredentials(true)
            .allowedOrigins("http://localhost:3000", "https://surl-fe.vercel.app")
            .maxAge(3600)
    }
}
