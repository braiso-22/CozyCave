package com.braiso_22.cozycave.feature_task.presentation.add_edit_task


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.braiso_22.cozycave.feature_task.domain.InvalidTaskException
import com.braiso_22.cozycave.feature_task.domain.Task
import com.braiso_22.cozycave.feature_task.domain.use_case.AddTaskUseCase
import com.braiso_22.cozycave.feature_task.domain.use_case.GetTaskByIdUseCase
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.AddEditTaskEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.S)
@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(AddEditTaskState())
    val state: State<AddEditTaskState> = _state
    private var updateStateJob: Job? = null

    private var _eventFlow = MutableSharedFlow<UiEvent>()

    private var currentTaskId: Int? = null

    init {
        savedStateHandle.get<Int>("taskId").let { taskId ->
            if (taskId != null) {
                viewModelScope.launch {
                    getTaskByIdUseCase(taskId)?.also {
                        currentTaskId = it.id
                        _state.value = AddEditTaskState(
                            name = it.name,
                            description = it.description ?: "",
                            days = it.days ?: "",
                            date = LocalDate.ofEpochDay(it.initialDate),
                            // should get the time from a unix timestamp initialDate
                            time = LocalTime.ofInstant(
                                Instant.ofEpochMilli(it.initialDate),
                                ZoneId.systemDefault()
                            )
                        )
                    }
                }
            }
        }
    }


    fun onEvent(event: AddEditTaskEvent) {

        when (event) {
            is EnteredName -> {
                _state.value = state.value.copy(name = event.name)
            }

            is EnteredDescription -> {
                _state.value = state.value.copy(description = event.description)
            }

            is EnteredDays -> {
                _state.value = state.value.copy(days = event.days)
            }

            is EnteredDate -> {
                _state.value = state.value.copy(
                    date = event.date
                )
            }

            is EnteredTime -> {
                _state.value = state.value.copy(
                    time = event.time
                )
            }

            is SaveTask -> {
                viewModelScope.launch {
                    try {
                        addTaskUseCase(
                            Task(
                                id = null,
                                name = state.value.name,
                                description = state.value.description,
                                frequency = "Daily",
                                initialDate = LocalDate.now().toEpochDay(),
                                days = state.value.days
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                    } catch (e: InvalidTaskException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save the task."
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveTask : UiEvent()
    }
}