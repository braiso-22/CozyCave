package com.braiso_22.cozycave.feature_task.presentation.tasks

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.braiso_22.cozycave.R
import com.braiso_22.cozycave.feature_task.presentation.tasks.components.TasksList
import com.braiso_22.cozycave.ui.common.isVertical

@Composable
fun TasksScreen(
    windowSizeClass: WindowSizeClass,
    onClickAddTask: () -> Unit,
    onClickTask: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    TasksScreenContent(
        tasks = state.tasks,
        windowSizeClass = windowSizeClass,
        onClickAddTask = onClickAddTask,
        onClickTask = onClickTask,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreenContent(
    tasks: List<TaskUiState>,
    windowSizeClass: WindowSizeClass,
    onClickAddTask: () -> Unit,
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
        ) {
            TasksList(
                tasks = tasks,
                modifier = Modifier.padding(it)
            )
        }
    } else {
        TasksList(tasks = tasks)
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun TasksScreenContentPreview(dpSize: DpSize) {
    val tasks = remember {
        mutableStateListOf(
            TaskUiState("Task 1", "Description 1"),
            TaskUiState("Task 2", "Description 2"),
            TaskUiState("Task 3", "Description 3"),
        )
    }
    TasksScreenContent(
        tasks = tasks,
        windowSizeClass = WindowSizeClass.calculateFromSize(dpSize),
        onClickAddTask = {},
        onClickTask = {},
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun TasksScreenContentVerticalPreview() {
    TasksScreenContentPreview(DpSize(360.dp, 720.dp))
}

@Preview(showBackground = true, widthDp = 720, heightDp = 360)
@Composable
fun TasksScreenContentHorizontalPreview() {
    TasksScreenContentPreview(DpSize(720.dp, 360.dp))
}
