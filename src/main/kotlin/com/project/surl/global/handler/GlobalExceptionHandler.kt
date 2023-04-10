package com.project.surl.global.handler

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.kotlin.core.publisher.toMono

@Configuration
class GlobalExceptionHandler(
    private val objectMapper: ObjectMapper
) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain) =
        chain.filter(exchange).onErrorResume { ex ->
            mono {
                val errorResponse = if (ex is ResponseStatusException) {
                    ErrorResponse(statusCode = ex.statusCode.value(), errorMessage = "${ex.reason}")
                } else {
                    ex.printStackTrace()
                    ErrorResponse(statusCode = 500, errorMessage = ex.message ?: "Internal Server Error")
                }

                with(exchange.response) {
                    this.statusCode = HttpStatusCode.valueOf(errorResponse.statusCode)
                    headers.contentType = MediaType.APPLICATION_JSON
                    headers.acceptCharset = listOf(Charsets.UTF_8)
                    val dataBuffer = bufferFactory().wrap(objectMapper.writeValueAsBytes(errorResponse))
                    writeWith(dataBuffer.toMono()).awaitSingle()
                }
            }
        }
}

data class ErrorResponse(
    val statusCode: Int,
    val errorMessage: String,
)
