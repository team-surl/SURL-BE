package com.project.surl.url.controller

import com.project.surl.url.service.UrlService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.reactive.result.view.RedirectView

@Controller
class RedirectController(
    private val urlService: UrlService,
) {

    @GetMapping("/{shortUrl}")
    suspend fun getUrl(@PathVariable shortUrl: String): RedirectView {
        val url = urlService.getUrl(shortUrl)
        return RedirectView(url)
    }
}
