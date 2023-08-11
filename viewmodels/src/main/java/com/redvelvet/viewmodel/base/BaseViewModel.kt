package com.redvelvet.viewmodel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redvelvet.entities.error.MovieError
import com.redvelvet.entities.error.NetworkError
import com.redvelvet.entities.error.NullResultError
import com.redvelvet.entities.error.ValidationError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : BaseUiState, UiEvent>(state: UiState) :
    ViewModel() {

    protected val _state = MutableStateFlow(state)
    val state = _state.asStateFlow()

    protected val _event = MutableSharedFlow<UiEvent>()
    val event = _event.asSharedFlow()

    fun <T> tryToExecute(
        execute: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (error: ErrorUiState) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = execute()
                onSuccess(result)
            } catch (e: ValidationError){
                onError(InvalidationErrorState())
            }catch (e: NullResultError){
                onError(NullResultErrorState())
            }catch (e: NetworkError){
                onError(NetworkErrorState())
            }catch (e: MovieError){
                onError(ErrorUiState())
            }
        }
    }

    protected fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            _event.emit(uiEvent)
        }
    }
}

interface BaseUiState
