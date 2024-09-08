package io.github.emusute1212.calculator.ui.main.section.specials

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.emusute1212.calculator.ui.main.CalculatorOnAction

data class SpecialsButtonSectionUiState(
    val canAllClear: Boolean,
    val onAction: CalculatorOnAction,
)

@Composable
fun rememberSpecialsButtonSectionUiState(
    canAllClear: Boolean,
    onAction: CalculatorOnAction,
): SpecialsButtonSectionUiState {
    return remember(
        canAllClear,
        onAction,
    ) {
        SpecialsButtonSectionUiState(
            canAllClear = canAllClear,
            onAction = onAction,
        )
    }
}
