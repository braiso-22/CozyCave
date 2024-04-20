package com.braiso_22.cozycave.router

import androidx.compose.foundation.layout.fillMaxSize
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
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.AddEditTaskScreen
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

    NavHost(
        navController = navController,
        startDestination = AppScreens.Tasks.route,
        modifier = modifier,
    ) {
        composable(AppScreens.Tasks.route) {
            TasksScreen(
                windowSizeClass = windowSizeClass,
                modifier = Modifier.fillMaxSize(),
                onClickAddTask = {
                    navController.navigate(AppScreens.AddEditTask.route + 0 + "/false")
                },
                onSeeDetail = {
                },
                onAddExecution = {},
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
    }

}