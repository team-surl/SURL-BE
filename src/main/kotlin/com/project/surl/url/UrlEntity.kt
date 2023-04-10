package com.project.surl.url

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("tbl_url")
data class UrlEntity(
    @Id
    val id: String = "${UUID.randomUUID()}",

    @Column
    val url: String,

    @Column
    val shortUrl: String,

    @Column
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Version
    private val version: Int = 0,
)
