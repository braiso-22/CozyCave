package com.braiso_22.cozycave.router

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.AddEditTaskScreen
import com.braiso_22.cozycave.feature_task.presentation.tasks.TasksScreen

/**
 * A composable function that sets up the navigation in the app
 */
@Composable
fun AppNavigation(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

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
                    navController.navigate(AppScreens.AddEditTask.route)
                },
                onClickTask = { taskId ->
                    navController.navigate("${AppScreens.AddEditTask.route}/$taskId")
                }
            )
        }
        composable(
            route = AppScreens.AddEditTask.route,
            arguments = listOf(
                navArgument(
                    name = "taskId",
                ) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            AddEditTaskScreen(
                modifier = Modifier.fillMaxSize(),
            )
        }
    }

}