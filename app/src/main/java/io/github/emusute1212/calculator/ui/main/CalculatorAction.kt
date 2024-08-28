package io.github.emusute1212.calculator.ui.main

import io.github.emusute1212.calculator.model.entity.Controller

fun interface CalculatorOnAction {
    operator fun invoke(action: CalculatorAction)
}

sealed interface CalculatorAction {
    data class OnClickControllerButton(
        val controller: Controller,
    ) : CalculatorAction
}