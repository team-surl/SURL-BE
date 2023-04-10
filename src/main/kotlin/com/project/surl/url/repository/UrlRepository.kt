package com.project.surl.url.repository

import com.project.surl.url.UrlEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface UrlRepository : CoroutineCrudRepository<UrlEntity, UUID> {

    suspend fun findByShortUrl(shortUrl: String): UrlEntity?

    suspend fun findByUrl(url: String): UrlEntity?
}
