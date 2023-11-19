package com.braiso_22.cozycave.feature_task.ui.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.braiso_22.cozycave.feature_task.ui.tasks.components.TasksList

@Composable
fun TasksScreen(viewModel: TasksViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Column {
        Row {
            Text(text = "Tasks")
        }
        TasksList(tasks = state.tasks)
    }
}