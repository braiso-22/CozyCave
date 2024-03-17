package com.braiso_22.cozycave.router

/**
 * Sealed class that represents the screens in the app
 */
sealed class AppScreens(val route: String) {
    object Tasks : AppScreens("tasks")
    object AddEditTask : AppScreens("add_edit_task/{taskId}")
}