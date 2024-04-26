package com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution

import com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution.state.AddEditExecutionUiState

sealed class AddEditExecutionEvent {
    data class ChangeState(val state: AddEditExecutionUiState) : AddEditExecutionEvent()
    data object Save : AddEditExecutionEvent()
    data object Cancel : AddEditExecutionEvent()
}