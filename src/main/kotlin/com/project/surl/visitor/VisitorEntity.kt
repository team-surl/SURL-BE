package com.project.surl.visitor

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("tbl_visitor")
data class VisitorEntity(
    @Id
    val id: String = "${UUID.randomUUID()}",

    @Column
    val urlId: String,

    @Column
    val ip: String,

    @Column
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Version
    private val version: Int = 0,
)
