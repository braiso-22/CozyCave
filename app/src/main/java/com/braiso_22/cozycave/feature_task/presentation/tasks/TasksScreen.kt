package com.braiso_22.cozycave.feature_task.presentation.tasks

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.braiso_22.cozycave.R
import com.braiso_22.cozycave.feature_task.presentation.tasks.components.TasksList
import com.braiso_22.cozycave.ui.common.isVertical
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TasksScreen(
    windowSizeClass: WindowSizeClass,
    onClickAddTask: () -> Unit,
    onSeeDetail: (Int) -> Unit,
    onAddExecution: (Int) -> Unit,
    onEdit: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is TasksViewModel.TaskUiEvent.ShowUndoDeletion -> {
                    val result = snackbarHostState.showSnackbar(
                        message = context.getString(R.string.task_deleted),
                        actionLabel = context.getString(R.string.undo)
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TasksEvent.UndoDeletion)
                    }
                }
            }
        }
    }

    TasksScreenContent(
        tasks = state.tasks,
        snackbarHostState = snackbarHostState,
        windowSizeClass = windowSizeClass,
        onClickAddTask = onClickAddTask,
        onClickTask = onSeeDetail,
        onDeleteTask = {
            viewModel.onEvent(TasksEvent.Delete(it))
        },
        onAddExecution = onAddExecution,
        onEdit = onEdit,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreenContent(
    tasks: List<TaskUiState>,
    snackbarHostState: SnackbarHostState,
    windowSizeClass: WindowSizeClass,
    onClickAddTask: () -> Unit,
    onAddExecution: (Int) -> Unit,
    onEdit: (Int) -> Unit,
    onDeleteTask: (Int) -> Unit,
    onClickTask: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (windowSizeClass.isVertical()) {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    title = { Text("Tasks") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    actions = {
                        IconButton(onClick = onClickAddTask) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = stringResource(R.string.add_new_task)
                            )
                        }
                    },
                )
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
        ) {
            TasksList(
                tasks = tasks,
                onClickEditTask = onEdit,
                onClickNewExecution = onAddExecution,
                onClickDelete = onDeleteTask,
                onClickSeeDetail = onClickTask,
                modifier = Modifier.padding(it)
            )
        }
    } else {
        TasksList(
            tasks = tasks,
            onClickEditTask = onClickTask,
            onClickNewExecution = onAddExecution,
            onClickDelete = onDeleteTask,
            onClickSeeDetail = onEdit,
        )
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun TasksScreenContentPreview(dpSize: DpSize) {
    val tasks = remember {
        mutableStateListOf(
            TaskUiState(
                id = 1,
                name = "Task 1",
                description = "Description 1",
            ),
            TaskUiState(
                id = 2,
                name = "Task 2",
                description = "Description 2",
            ),
            TaskUiState(
                id = 3,
                name = "Task 3",
                description = "Description 3",
            ),
        )
    }
    TasksScreenContent(
        tasks = tasks,
        windowSizeClass = WindowSizeClass.calculateFromSize(dpSize),
        snackbarHostState = SnackbarHostState(),
        onClickAddTask = {},
        onClickTask = {},
        onDeleteTask = {},
        onEdit = {},
        onAddExecution = {},
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun TasksScreenContentVerticalPreview() {
    TasksScreenContentPreview(DpSize(360.dp, 720.dp))
}

@Preview(showBackground = true, widthDp = 720, heightDp = 360)
@Composable
private fun TasksScreenContentHorizontalPreview() {
    TasksScreenContentPreview(DpSize(720.dp, 360.dp))
}
