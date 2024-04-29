package com.braiso_22.cozycave.router

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution.AddEditExecutionScreen
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.AddEditTaskScreen
import com.braiso_22.cozycave.feature_task.presentation.show_task_detail.TaskDetailScreen
import com.braiso_22.cozycave.feature_task.presentation.tasks.TasksScreen
import com.braiso_22.cozycave.router.NavigationViewModel.UiEvent
import kotlinx.coroutines.flow.collectLatest

/**
 * A composable function that sets up the navigation in the app
 */
@Composable
fun AppNavigation(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navigationViewModel: NavigationViewModel = hiltViewModel(),
) {

    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = Unit) {
        navigationViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is NavigationEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = modifier
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.Tasks.route,
            modifier = Modifier.padding(padding),
        ) {
            composable(AppScreens.Tasks.route) {
                TasksScreen(
                    windowSizeClass = windowSizeClass,
                    modifier = Modifier.fillMaxSize(),
                    onClickAddTask = {
                        navController.navigate(AppScreens.AddEditTask.route + 0 + "/false")
                    },
                    onSeeDetail = { taskId ->
                        navController.navigate(AppScreens.TaskDetail.route + taskId)
                    },
                    onAddExecution = { taskId ->
                        navController.navigate(AppScreens.AddEditExecution.route + taskId + "/true" + "/0")
                    },
                    onEdit = { taskId ->
                        navController.navigate(AppScreens.AddEditTask.route + taskId + "/true")
                    },
                )
            }
            composable(
                route = AppScreens.AddEditTask.route + "{taskId}" + "/{edit}",
                arguments = listOf(
                    navArgument(
                        name = "taskId",
                    ) {
                        type = NavType.IntType
                        defaultValue = 0
                    },
                    navArgument(
                        name = "edit",
                    ) {
                        type = NavType.BoolType
                        defaultValue = false
                    }
                )
            ) {
                AddEditTaskScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onUiMessage = { message ->
                        navigationViewModel.onEvent(
                            UiEvent.ShowSnackBar(message)
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                )
            }

            composable(
                route = AppScreens.AddEditExecution.route + "{relatedId}" + "/{edit}" + "/{executionId}",
                arguments = listOf(
                    navArgument(
                        name = "relatedId",
                    ) {
                        type = NavType.IntType
                        defaultValue = 0
                    },
                    navArgument(
                        name = "edit",
                    ) {
                        type = NavType.BoolType
                        defaultValue = false
                    },
                    navArgument(
                        name = "executionId",
                    ) {
                        type = NavType.IntType
                        defaultValue = 0
                    }
                )
            ) {
                AddEditExecutionScreen(
                    windowSizeClass = windowSizeClass,
                    onMessage = {
                        navigationViewModel.onEvent(UiEvent.ShowSnackBar(it))
                    },
                    onClickBack = {
                        navController.popBackStack()
                    },
                    modifier = modifier.fillMaxSize()
                )
            }
            composable(
                route = AppScreens.TaskDetail.route + "{taskId}",
                arguments = listOf(
                    navArgument(
                        name = "taskId",
                    ) {
                        type = NavType.IntType
                        defaultValue = 0
                    },
                )
            ) {
                TaskDetailScreen(
                    windowSizeClass = windowSizeClass,
                    onBack = {
                        navController.popBackStack()
                    },
                    onClickTaskExecution = { taskId, executionId ->
                        navController.navigate(AppScreens.AddEditExecution.route + taskId + "/true/" + executionId)
                    },
                    modifier = modifier.fillMaxSize()
                )
            }
        }
    }
}