package com.redvelvet.viewmodel.onboarding

import androidx.lifecycle.viewModelScope
import com.redvelvet.usecase.usecase.user.SetUserNotFirstTimeUseAppUseCase
import com.redvelvet.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val setUserNotFirstTimeUseApp: SetUserNotFirstTimeUseAppUseCase,
) : BaseViewModel<OnBoardingUiState, Unit>(OnBoardingUiState()) {

    fun setNotFirstTimeUseApp() {
        viewModelScope.launch(Dispatchers.IO) {
            setUserNotFirstTimeUseApp.invoke()
            _state.update {
                it.copy(
                    saved = true,
                )
            }
        }
    }
}