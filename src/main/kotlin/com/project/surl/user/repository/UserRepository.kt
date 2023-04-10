package com.project.surl.user.repository

import com.project.surl.user.UserEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface UserRepository : CoroutineCrudRepository<UserEntity, UUID> {
    suspend fun findByAccountId(accountId: String): UserEntity?
}
