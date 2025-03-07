package com.redvelvet.usecase.usecase.auth

import com.redvelvet.usecase.repository.AuthRepository
import javax.inject.Inject

class GetSavedAccountDetailsIdUsecase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(): Int {
        return authRepository.getAccountDetailsIdFromLocal() ?: 0
    }
}

