package com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.braiso_22.cozycave.date_time_selector.presentation.date_selector.DateSelector
import com.braiso_22.cozycave.date_time_selector.presentation.time_selector.TimeSelector

@Composable
fun DateTimeRow(
    date: String,
    setDate: (String) -> Unit,
    time: String,
    setTime: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Row {
            Spacer(Modifier.padding(horizontal = 20.dp))
            DateSelector(
                state = date,
                setState = setDate,
                modifier = Modifier
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TimeSelector(
                state = time,
                setState = setTime,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}

@Preview
@Composable
private fun DateTimeRowPreview() {
    DateTimeRow(
        date = "",
        setDate = {},
        time = "",
        setTime = {},
        modifier = Modifier.fillMaxWidth()
    )
}