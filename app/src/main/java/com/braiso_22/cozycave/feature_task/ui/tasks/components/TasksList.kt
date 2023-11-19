package com.braiso_22.cozycave.feature_task.ui.tasks.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.braiso_22.cozycave.feature_task.ui.tasks.TaskUiState

@Composable
fun TasksList(tasks: List<TaskUiState>) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(taskUiState = task)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TasksListPreview() {
    TasksList(
        listOf(
            TaskUiState(
                name = "Task 1",
                description = "Description 1",
                days = "Monday, Tuesday, Wednesday, Friday, Saturday"
            ),
            TaskUiState(
                name = "Task 2",
                description = "Description 2",
                days = "Monday, Wednesday, Thursday, Saturday, Sunday"
            ),
        )
    )
}