package com.braiso_22.cozycave.feature_execution.domain

import android.content.IntentSender.OnFinished
import java.time.LocalDateTime

/**
 * Represents an execution of a feature.
 */
data class Execution(
    val id: Int = 0,
    val startDateTime: LocalDateTime = LocalDateTime.now(),
    val endDateTime: LocalDateTime = LocalDateTime.now(),
    val relatedId: Int,
    val finished: Boolean = false,
)
