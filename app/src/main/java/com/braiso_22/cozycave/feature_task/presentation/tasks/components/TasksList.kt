package com.braiso_22.cozycave.feature_task.presentation.tasks.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.braiso_22.cozycave.feature_task.presentation.tasks.TaskUiState

@Composable
fun TasksList(
    tasks: List<TaskUiState>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(tasks) { task ->
            TaskItem(
                taskUiState = task,
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            )
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