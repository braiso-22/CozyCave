package com.braiso_22.cozycave.feature_task.presentation.tasks.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.braiso_22.cozycave.feature_task.presentation.tasks.TaskUiState

@Composable
fun TaskItem(
    state: TaskUiState,
    onClickNewExecution: (Int) -> Unit,
    onClickEdit: (Int) -> Unit,
    onClickDeleteItem: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = state.name,
                style = MaterialTheme.typography.titleLarge
            )
            Row {
                IconButton(onClick = {
                    onClickDeleteItem(state.id)
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete this task"
                    )
                }
                IconButton(onClick = {
                    onClickEdit(state.id)
                }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit this task"
                    )
                }
                IconButton(onClick = {
                    onClickNewExecution(state.id)
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add new execution"
                    )
                }
            }
        }
        Text(
            text = state.description,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }

}

@Preview
@Composable
fun TaskItemPreview() {
    Surface {
        TaskItem(
            TaskUiState(
                name = "Task 1",
                description = "Description 1",
            ),
            onClickDeleteItem = {},
            onClickEdit = {},
            onClickNewExecution = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
