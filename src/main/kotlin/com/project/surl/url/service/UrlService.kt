package com.project.surl.url.service

import com.project.surl.global.random.RandomUtils
import com.project.surl.url.UrlEntity
import com.project.surl.url.controller.dto.request.GenerateShortUrlRequest
import com.project.surl.url.controller.dto.response.GetShortUrlResponse
import com.project.surl.url.controller.dto.response.GetUrlResponse
import com.project.surl.url.repository.UrlRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UrlService(
    private val urlRepository: UrlRepository,
) {

    companion object {
        private const val BASE_URL = "http://13.125.198.137:8080"
    }

    suspend fun generateShortUrl(request: GenerateShortUrlRequest): GetShortUrlResponse {
        urlRepository.findByUrl(request.url)?.let {
            return GetShortUrlResponse("${BASE_URL}/${it.shortUrl}")
        }

        val urlEntity = UrlEntity(
            url = request.url,
            shortUrl = RandomUtils.generateRandomString(6),
        )
        urlRepository.save(urlEntity)

        return GetShortUrlResponse("${BASE_URL}/${urlEntity.shortUrl}")
    }

    suspend fun getUrl(shortUrl: String): GetUrlResponse {
        val urlEntity = urlRepository.findByShortUrl(shortUrl)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "SURL Not Found.")

        return GetUrlResponse(urlEntity.url)
    }
}
