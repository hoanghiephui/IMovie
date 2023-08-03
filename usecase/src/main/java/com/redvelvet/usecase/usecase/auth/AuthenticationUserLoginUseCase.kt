package com.redvelvet.usecase.usecase.auth

import com.redvelvet.entities.auth.Session
import com.redvelvet.usecase.repository.AuthRepository
import javax.inject.Inject

class AuthenticationUserLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val createRequestTokenUseCase: CreateRequestTokenUseCase,
    private val validateUserWithUserNameAndPasswordUseCase:
    ValidateUserWithUserNameAndPasswordUseCase,
) {
    suspend operator fun invoke(userName: String, password: String): Session {
        val tokenRequest = createRequestTokenUseCase()
        validateUserWithUserNameAndPasswordUseCase(
            userName = userName,
            password = password,
            requestToken = tokenRequest
        )
        return authRepository.createUserSession(token = tokenRequest)
    }

}