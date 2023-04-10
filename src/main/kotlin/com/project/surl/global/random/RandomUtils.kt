package com.project.surl.global.random

import java.security.SecureRandom

object RandomUtils {

    fun generateRandomString(length: Int): String {
        val secureRandom = SecureRandom()

        val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { secureRandom.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}
