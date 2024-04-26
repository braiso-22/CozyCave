package com.braiso_22.cozycave.feature_task.presentation.show_task_detail.components

import androidx.compose.foundation.layout.*
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
fun CompletedExecutionComponent(
    state: ExecutionUiState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (state.endDate == state.startDate) {
                Text(
                    text = state.startDate,
                    modifier = Modifier.padding(8.dp)
                )
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = null
                )
            } else {
                Text(
                    text = state.startDate,
                    modifier = Modifier.padding(8.dp)
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "From ${state.startDate} to ${state.endDate}"
                )
                Text(
                    text = state.endDate,
                    modifier = Modifier.padding(8.dp)
                )
            }
            if (state.startHour == state.endHour) {
                Text(text = state.startHour)
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null
                )
            } else {
                Text(text = state.startHour)
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "From ${state.startHour} to ${state.endHour}"
                )
                Text(text = state.endHour)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExecutionComponentPreview() {
    CompletedExecutionComponent(
        state = ExecutionUiState(
            startDate = "00/00/0000",
            endDate = "00/07/0000",
            startHour = "00:00",
            endHour = "00:00",
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}