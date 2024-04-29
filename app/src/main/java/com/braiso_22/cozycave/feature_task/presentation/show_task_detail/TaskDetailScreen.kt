package com.braiso_22.cozycave.feature_task.presentation.show_task_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.braiso_22.cozycave.R
import com.braiso_22.cozycave.feature_task.presentation.show_task_detail.components.CompletedExecutionComponent
import com.braiso_22.cozycave.feature_task.presentation.show_task_detail.components.UnCompletedExecutionComponent
import com.braiso_22.cozycave.feature_task.presentation.show_task_detail.state.TaskDetailUiState

@Composable
fun TaskDetailScreen(
    windowSizeClass: WindowSizeClass,
    onBack: () -> Unit,
    onClickTaskExecution: (taskId: Int, executionId: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TaskDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    TaskDetailScreenContent(
        state = state,
        windowSizeClass = windowSizeClass,
        onBack = onBack,
        onClickTaskExecution = onClickTaskExecution,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreenContent(
    state: TaskDetailUiState,
    windowSizeClass: WindowSizeClass,
    onClickTaskExecution: (Int, Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.task_detail)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                Text(
                    text = state.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                if (state.completedExecutions.isEmpty() && state.unFinishedExecutions.isEmpty()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "No executions",
                            Modifier.padding(8.dp)
                        )
                    }
                }
            }

            item {
                if (state.unFinishedExecutions.isNotEmpty()) {
                    Text(
                        text = "Not finished executions",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))

                }
            }
            itemsIndexed(state.unFinishedExecutions) { index, execution ->
                UnCompletedExecutionComponent(
                    state = execution,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clickable {
                            onClickTaskExecution(state.taskId, execution.id)
                        }
                )
                if (index != state.unFinishedExecutions.lastIndex)
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp)
                    )
            }

            item {
                if (state.completedExecutions.isNotEmpty()) {
                    Text(
                        text = "Finished executions",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }

            itemsIndexed(state.completedExecutions) { index, execution ->
                CompletedExecutionComponent(
                    state = execution,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clickable {
                            onClickTaskExecution(state.taskId, execution.id)
                        }
                )
                if (index != state.completedExecutions.lastIndex)
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp)
                    )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun TaskDetailScreenContentPreview(dpSize: DpSize) {
    val state = remember {
        mutableStateOf(
            TaskDetailUiState(taskId = 0),
        )
    }
    TaskDetailScreenContent(
        state = state.value,
        windowSizeClass = WindowSizeClass.calculateFromSize(dpSize),
        onBack = {},
        onClickTaskExecution = { taskId, executionId ->

        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun TaskDetailScreenContentVerticalPreview() {
    TaskDetailScreenContentPreview(DpSize(360.dp, 720.dp))
}

@Preview(showBackground = true, widthDp = 720, heightDp = 360)
@Composable
private fun TaskDetailScreenContentHorizontalPreview() {
    TaskDetailScreenContentPreview(DpSize(720.dp, 360.dp))
}