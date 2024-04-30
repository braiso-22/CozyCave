package com.braiso_22.cozycave.feature_task.presentation.show_task_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.braiso_22.cozycave.R
import com.braiso_22.cozycave.feature_task.presentation.show_task_detail.state.ExecutionUiState

@Composable
fun ExecutionComponent(
    state: ExecutionUiState,
    canModify: Boolean,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Executor user",
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Column(modifier = Modifier.weight(0.5f)) {
                FromToRow(
                    start = state.startDate,
                    end = state.endDate,
                    imageVector = Icons.Default.CalendarMonth,
                    Modifier.fillMaxWidth()
                )
                FromToRow(
                    start = state.startHour,
                    end = state.endHour,
                    imageVector = Icons.Default.AccessTime,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row {
                IconButton(onClick = { onDelete(state.id) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete_this_execution)
                    )
                }
                if (canModify) {
                    IconButton(onClick = { onEdit(state.id) }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(R.string.delete_this_execution)
                        )
                    }
                } else {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.TaskAlt,
                            contentDescription = stringResource(R.string.delete_this_execution)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FromToRow(
    start: String,
    end: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (start == end) {
            Text(
                text = start,
                modifier = Modifier.padding(8.dp)
            )
            Icon(
                imageVector = imageVector,
                contentDescription = null
            )

        } else {
            Text(
                text = start,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "From $start to $end"
            )
            Text(
                text = end,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExecutionComponentPreview() {
    ExecutionComponent(
        state = ExecutionUiState(
            id = 0,
            startDate = "00/01/0000",
            endDate = "00/00/0000",
            startHour = "01:00",
            endHour = "00:00",
        ),
        canModify = true,
        onDelete = {},
        onEdit = {},
        modifier = Modifier
            .fillMaxWidth()
    )
}