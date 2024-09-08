package io.github.emusute1212.calculator.ui.main.section.numbers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.emusute1212.calculator.ui.main.CalculatorOnAction

data class NumberButtonSectionUiState(
    val onAction: CalculatorOnAction,
)

@Composable
fun rememberNumberButtonSectionUiState(
    onAction: CalculatorOnAction,
): NumberButtonSectionUiState {
    return remember(
        onAction,
    ) {
        NumberButtonSectionUiState(
            onAction = onAction,
        )
    }
}
