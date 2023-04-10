package com.project.surl.user.service

import com.project.surl.global.bcrypt.BcryptUtils
import com.project.surl.global.jwt.JwtClaim
import com.project.surl.global.jwt.JwtProperties
import com.project.surl.global.jwt.JwtUtils
import com.project.surl.user.UserEntity
import com.project.surl.user.controller.dto.request.SignInRequest
import com.project.surl.user.controller.dto.request.SignUpRequest
import com.project.surl.user.controller.dto.response.TokenResponse
import com.project.surl.user.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtProperties: JwtProperties,
) {

    suspend fun signUp(request: SignUpRequest) {
        with(request) {
            userRepository.findByAccountId(accountId)
                ?.let { throw ResponseStatusException(HttpStatus.CONFLICT, "AccountId Exists.") }

            val userEntity = UserEntity(
                accountId = accountId,
                username = username,
                password = BcryptUtils.encode(password),
            )
            userRepository.save(userEntity)
        }
    }

    suspend fun signIn(request: SignInRequest): TokenResponse =
        with(request) {
            val userEntity = userRepository.findByAccountId(accountId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "AccountId Not Found.")

            if (!BcryptUtils.verify(rawPassword = password, encodedPassword = userEntity.password)) {
                throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password Not Match.")
            }

            val claim = JwtClaim(accountId = userEntity.accountId, username = userEntity.username)
            val token = JwtUtils.generateToken(claim, jwtProperties)
            TokenResponse(accessToken = token)
        }
}
