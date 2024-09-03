package io.github.emusute1212.calculator.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import io.github.emusute1212.calculator.extensitons.CalculationMathContext
import io.github.emusute1212.calculator.extensitons.toFormattedNumber
import io.github.emusute1212.calculator.extensitons.toStringWithMathContext
import io.github.emusute1212.calculator.model.entity.Controller
import io.github.emusute1212.calculator.ui.main.section.numbers.NumberButtonSectionUiState
import io.github.emusute1212.calculator.ui.main.section.numbers.rememberNumberButtonSectionUiState
import io.github.emusute1212.calculator.ui.main.section.operators.OperatorButtonSectionUiState
import io.github.emusute1212.calculator.ui.main.section.operators.rememberOperatorButtonSectionUiState
import io.github.emusute1212.calculator.ui.main.section.specials.SpecialsButtonSectionUiState
import io.github.emusute1212.calculator.ui.main.section.specials.rememberSpecialsButtonSectionUiState
import timber.log.Timber

data class CalculatorScreenUiState(
    val calculationHistory: String,
    val displayText: String,
    val numberButtonSectionUiState: NumberButtonSectionUiState,
    val operatorButtonSectionUiState: OperatorButtonSectionUiState,
    val specialsButtonSectionUiState: SpecialsButtonSectionUiState,
)

@Composable
fun rememberCalculatorScreenUiState(
    viewModel: CalculatorViewModel,
): CalculatorScreenUiState {
    val calculationHistory = remember(viewModel.calculationHistories) {
        viewModel.calculationHistories.joinToString("") {
            it.number
                .toStringWithMathContext(CalculationMathContext)
                .toFormattedNumber() + it.operator.text
        }
    }
    val displayText = when (viewModel.calculatorMode) {
        CalculatorViewModel.CalculatorMode.IdleInput,
        CalculatorViewModel.CalculatorMode.InputtingNumber,
        CalculatorViewModel.CalculatorMode.InputClear -> {
            viewModel.inputText.toFormattedNumber()
        }

        CalculatorViewModel.CalculatorMode.FixOperator,
        CalculatorViewModel.CalculatorMode.Calculated -> {
            viewModel.result.toStringWithMathContext(CalculationMathContext).toFormattedNumber()
        }

        CalculatorViewModel.CalculatorMode.Error -> {
            "エラー"
        }
    }
    val canAllClear = (viewModel.calculatorMode == CalculatorViewModel.CalculatorMode.IdleInput
            || viewModel.calculatorMode == CalculatorViewModel.CalculatorMode.InputClear
            || viewModel.calculatorMode == CalculatorViewModel.CalculatorMode.Error)
    val onAction = rememberCalculatorOnAction(
        viewModel = viewModel,
    )
    val numberButtonSectionUiState = rememberNumberButtonSectionUiState(
        onAction = onAction
    )
    val operatorButtonSectionUiState = rememberOperatorButtonSectionUiState(
        onAction = onAction
    )
    val specialsButtonSectionUiState = rememberSpecialsButtonSectionUiState(
        canAllClear = canAllClear,
        onAction = onAction,
    )
    return remember(
        calculationHistory,
        displayText,
        numberButtonSectionUiState,
        operatorButtonSectionUiState,
        specialsButtonSectionUiState,
    ) {
        CalculatorScreenUiState(
            calculationHistory = calculationHistory,
            displayText = displayText,
            numberButtonSectionUiState = numberButtonSectionUiState,
            operatorButtonSectionUiState = operatorButtonSectionUiState,
            specialsButtonSectionUiState = specialsButtonSectionUiState,
        )
    }
}

@Composable
private fun rememberCalculatorOnAction(
    viewModel: CalculatorViewModel,
): CalculatorOnAction {
    val haptic = LocalHapticFeedback.current
    return remember(
        viewModel,
    ) {
        CalculatorOnAction { action ->
            when (action) {
                is CalculatorAction.OnClickControllerButton -> {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    when (action.controller) {
                        is Controller.Numbers -> {
                            viewModel.onInputNumber(action.controller)
                        }

                        is Controller.Specials -> {
                            viewModel.onInputSpecial(action.controller)
                        }

                        is Controller.Operators -> {
                            viewModel.onInputOperator(action.controller)
                        }
                    }
                }
            }
        }
    }
}
