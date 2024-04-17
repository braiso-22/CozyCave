package com.braiso_22.cozycave.router

sealed class NavigationEvent {
    data class ShowSnackBar(val message: String) : NavigationEvent()
}