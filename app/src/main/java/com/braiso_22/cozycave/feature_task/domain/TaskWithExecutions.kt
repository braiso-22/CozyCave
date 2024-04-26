package com.braiso_22.cozycave.feature_task.domain

import com.braiso_22.cozycave.feature_execution.domain.Execution

data class TaskWithExecutions(
    val task: Task,
    val executions: List<Execution>,
)