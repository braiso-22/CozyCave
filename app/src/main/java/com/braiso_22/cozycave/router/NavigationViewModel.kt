package com.braiso_22.cozycave.router

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NavigationViewModel : ViewModel() {

    private val _eventFlow = MutableSharedFlow<NavigationEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: UiEvent) = viewModelScope.launch {
        when (event) {
            is UiEvent.ShowSnackBar -> {
                _eventFlow.emit(NavigationEvent.ShowSnackBar(event.message))
            }
        }
    }


    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
    }
}