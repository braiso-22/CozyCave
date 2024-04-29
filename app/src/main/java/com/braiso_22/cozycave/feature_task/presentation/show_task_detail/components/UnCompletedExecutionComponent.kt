package com.braiso_22.cozycave.feature_task.presentation.show_task_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.braiso_22.cozycave.feature_task.presentation.show_task_detail.state.ExecutionUiState

@Composable
fun UnCompletedExecutionComponent(
    state: ExecutionUiState,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(text = state.startDate)
        Icon(
            imageVector = Icons.Default.CalendarMonth,
            contentDescription = null
        )
        Text(text = state.startHour)
        Icon(
            imageVector = Icons.Default.AccessTime,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UnCompletedExecutionComponentPreview() {
    UnCompletedExecutionComponent(
        state = ExecutionUiState(
            id = 0,
            startDate = "22/03/2002",
            endDate = "22/03/2002",
            startHour = "01:00",
            endHour = "12:00",
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}