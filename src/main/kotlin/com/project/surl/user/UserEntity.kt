package com.project.surl.user

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("tbl_user")
data class UserEntity(
    @Id
    val id: String = "${UUID.randomUUID()}",

    @Column
    val username: String,

    @Column
    val password: String,

    @Column
    val accountId: String,

    @Column
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Version
    private val version: Int = 0,
)
