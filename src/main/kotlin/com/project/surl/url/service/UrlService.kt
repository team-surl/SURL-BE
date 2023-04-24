package com.project.surl.url.service

import com.project.surl.global.random.RandomUtils
import com.project.surl.url.UrlEntity
import com.project.surl.url.controller.dto.request.GenerateShortUrlRequest
import com.project.surl.url.controller.dto.response.GetShortUrlResponse
import com.project.surl.url.repository.UrlRepository
import com.project.surl.visitor.VisitorEntity
import com.project.surl.visitor.repository.VisitorRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class UrlService(
    private val urlRepository: UrlRepository,
    private val visitorRepository: VisitorRepository,
) {

    companion object {
        private const val BASE_URL = "http://13.125.198.137:8080"
    }

    @Transactional
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

    @Transactional
    suspend fun getUrl(shortUrl: String, ip: String): String {

        val urlEntity = urlRepository.findByShortUrl(shortUrl)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "SURL Not Found.")

        val visitorEntity = VisitorEntity(
            urlId = urlEntity.id,
            ip = ip,
        )
        visitorRepository.save(visitorEntity)

        return urlEntity.url
    }
}
