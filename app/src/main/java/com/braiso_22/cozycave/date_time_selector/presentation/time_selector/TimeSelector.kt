package com.braiso_22.cozycave.date_time_selector.presentation.time_selector

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.braiso_22.cozycave.date_time_selector.presentation.time_selector.comps.TimePickerDialogWrapper
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TimeSelector(
    state: String,
    setState: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val time = try {
        LocalTime.parse(state)
    } catch (e: Exception) {
        LocalTime.now()
    }

    DateTimeSelectorContent(
        time = time,
        setTime = {
            setState(
                it.format(DateTimeFormatter.ofPattern("HH:mm"))
            )
        },
        modifier = modifier
    )
}

@Composable
private fun DateTimeSelectorContent(
    time: LocalTime,
    setTime: (LocalTime) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isTimeOpen by remember {
        mutableStateOf(false)
    }

    TimePickerDialogWrapper(
        isVisible = isTimeOpen,
        setVisible = { isTimeOpen = it },
        state = TimeState(time.hour, time.minute),
        setState = {
            setTime(LocalTime.of(it.hour, it.minute))
        }
    )

    Text(
        text = time.format(DateTimeFormatter.ofPattern("HH:mm")),
        modifier = modifier.clickable {
            isTimeOpen = true
        }
    )
}

@Preview
@Composable
private fun DateTimeSelectorPreview() {
    Surface {
        TimeSelector(
            state = "",
            setState = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
    }
}