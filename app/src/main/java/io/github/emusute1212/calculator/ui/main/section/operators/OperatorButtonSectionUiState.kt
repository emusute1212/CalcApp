package io.github.emusute1212.calculator.ui.main.section.operators

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.emusute1212.calculator.ui.main.CalculatorOnAction

data class OperatorButtonSectionUiState(
    val onAction: CalculatorOnAction,
)

@Composable
fun rememberOperatorButtonSectionUiState(
    onAction: CalculatorOnAction,
): OperatorButtonSectionUiState {
    return remember(
        onAction,
    ) {
        OperatorButtonSectionUiState(
            onAction = onAction,
        )
    }
}
