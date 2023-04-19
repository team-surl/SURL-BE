package com.project.surl.url.controller

import com.project.surl.url.controller.dto.request.GenerateShortUrlRequest
import com.project.surl.url.controller.dto.response.GetShortUrlResponse
import com.project.surl.url.controller.dto.response.GetUrlResponse
import com.project.surl.url.service.UrlService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UrlController(
    private val urlService: UrlService,
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/short-url")
    suspend fun generateShortUrl(@RequestBody request: GenerateShortUrlRequest): GetShortUrlResponse {
        return urlService.generateShortUrl(request)
    }

    @GetMapping("/{shortUrl}")
    suspend fun getUrl(@PathVariable shortUrl: String): String {
        return urlService.getUrl(shortUrl)
    }
}
