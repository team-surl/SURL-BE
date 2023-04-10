package com.project.surl.global.bcrypt

import at.favre.lib.crypto.bcrypt.BCrypt

object BcryptUtils {

    fun encode(rawPassword: String): String =
        BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray())

    fun verify(rawPassword: String, encodedPassword: String) =
        BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword).verified
}
