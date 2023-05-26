package com.project.surl.global.ip

import com.project.surl.global.ip.annotation.ClientIp
import org.springframework.core.MethodParameter
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class ClientIpArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter) =
        parameter.hasParameterAnnotation(ClientIp::class.java)

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange,
    ): Mono<Any> {
        val clientIp = exchange.request.headers.getFirst("X-Forwarded-For")
        return Mono.just(clientIp ?: "127.0.0.1")
    }
}
