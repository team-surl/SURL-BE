package com.project.surl.url.controller

import com.project.surl.global.ip.annotation.ClientIp
import com.project.surl.url.service.UrlService
import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.reactive.result.view.RedirectView

@Controller
class RedirectController(
    private val urlService: UrlService,
) {

    @GetMapping("/{shortUrl}")
    suspend fun getUrl(
        @ClientIp clientIp: String,
        @PathVariable shortUrl: String,
    ): RedirectView {
        val redirectUrl = urlService.getUrl(shortUrl, clientIp)
        return RedirectView(redirectUrl).apply {
            statusCode = MOVED_PERMANENTLY
        }
    }
}
