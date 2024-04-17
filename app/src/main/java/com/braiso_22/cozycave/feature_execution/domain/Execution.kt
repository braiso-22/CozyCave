package com.braiso_22.cozycave.feature_execution.domain

import java.time.LocalDateTime

data class Execution(
    val id: Int = 0,
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val relatedId: Int,
)
