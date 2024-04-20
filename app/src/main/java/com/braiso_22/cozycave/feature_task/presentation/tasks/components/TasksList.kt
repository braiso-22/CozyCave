package com.braiso_22.cozycave.feature_task.presentation.tasks.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.braiso_22.cozycave.feature_task.presentation.tasks.TaskUiState

@Composable
fun TasksList(
    tasks: List<TaskUiState>,
    onClickNewExecution: (Int) -> Unit,
    onClickEditTask: (Int) -> Unit,
    onClickSeeDetail: (Int) -> Unit,
    onClickDelete: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(tasks) { index, task ->
            TaskItem(
                state = task,
                onClickDeleteItem = onClickDelete,
                onClickEdit = onClickEditTask,
                onClickNewExecution = onClickNewExecution,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClickSeeDetail(task.id)
                    }
            )
            if (index != tasks.lastIndex)
                HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))
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
            ),
            TaskUiState(
                name = "Task 2",
                description = "Description 2",
            ),
        ),
        onClickSeeDetail = {},
        onClickNewExecution = {},
        onClickEditTask = {},
        onClickDelete = {}
    )
}