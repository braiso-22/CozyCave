package com.braiso_22.cozycave.date_time_selector.presentation.date_selector

import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.braiso_22.cozycave.date_time_selector.presentation.date_selector.comps.DatePickerDialogWrapper
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateSelector(
    state: String,
    setState: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val date = try {
        LocalDate.parse(state, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    } catch (e: Exception) {
        LocalDate.now()
    }

    DateSelectorComponent(
        date = date,
        setDate = {
            setState(
                it.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            )
        },
        modifier = modifier
    )
}

@Composable
private fun DateSelectorComponent(
    date: LocalDate,
    setDate: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isDateOpen by remember {
        mutableStateOf(false)
    }
    DatePickerDialogWrapper(
        isVisible = isDateOpen,
        setVisible = { isDateOpen = it },
        state = date.toEpochDay().times(86_400_000),
        setState = {
            setDate(
                try {
                    LocalDate.ofEpochDay(it.div(86_400_000))
                } catch (e: Exception) {
                    LocalDate.now()
                }
            )
        }
    )
    Text(
        text = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        modifier = modifier.clickable {
            isDateOpen = true
        }
    )
}

@Preview
@Composable
private fun DateSelectorPreview() {
    DateSelector(
        state = "",
        setState = {}
    )
}