package com.braiso_22.cozycave.feature_task.ui.tasks.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.braiso_22.cozycave.feature_task.ui.tasks.TaskUiState

@Composable
fun TaskItem(taskUiState: TaskUiState) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = taskUiState.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = taskUiState.description ?: "")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = taskUiState.days ?: "")
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(
        TaskUiState(
            name = "Task 1",
            description = "Description 1",
            days = "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday"
        )
    )
}