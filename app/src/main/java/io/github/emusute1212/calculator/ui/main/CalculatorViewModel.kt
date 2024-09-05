package io.github.emusute1212.calculator.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.emusute1212.calculator.extensitons.CalculationMathContext
import io.github.emusute1212.calculator.extensitons.MaxInputNumberSize
import io.github.emusute1212.calculator.model.entity.CalcEntity
import io.github.emusute1212.calculator.model.entity.Controller
import io.github.emusute1212.calculator.model.entity.Controller.Operators.Divide
import io.github.emusute1212.calculator.model.entity.Controller.Operators.Equal
import io.github.emusute1212.calculator.model.entity.Controller.Operators.Minus
import io.github.emusute1212.calculator.model.entity.Controller.Operators.Plus
import io.github.emusute1212.calculator.model.entity.Controller.Operators.Times
import timber.log.Timber
import java.math.BigDecimal

class CalculatorViewModel : ViewModel() {
    var inputText by mutableStateOf("0")
    var result: BigDecimal by mutableStateOf(BigDecimal.ZERO)
    var calculationHistories by mutableStateOf(listOf<CalcEntity>())
    var calculatorMode by mutableStateOf(CalculatorMode.IdleInput)

    fun onInputNumber(number: Controller.Numbers) {
        if (shouldBeInputText(number)) return
        if (
            calculatorMode == CalculatorMode.Calculated
            || calculatorMode == CalculatorMode.InputClear
        ) {
            clearHistoryAndResult()
        }

        calculatorMode = CalculatorMode.InputtingNumber

        if (number != Controller.Numbers.Dot && inputText == "0") {
            // Clear zero in the top.
            inputText = ""
        }
        inputText += number.text
    }

    fun onInputSpecial(specials: Controller.Specials) {
        when (specials) {
            Controller.Specials.AllClear -> onInputAllClear()
            Controller.Specials.Clear -> onInputClear()
            Controller.Specials.Switch -> onInputSwitch()
            Controller.Specials.Percent -> onInputPercent()
        }
    }

    fun onInputOperator(operator: Controller.Operators) {
        if (calculatorMode == CalculatorMode.Error) return
        if (operator == Equal) {
            onInputEqual()
        } else {
            onInputCalculate(
                result,
                inputText.toBigDecimal(),
                operator,
            )
        }
    }

    private fun onInputCalculate(
        result: BigDecimal,
        input: BigDecimal,
        operator: Controller.Operators,
    ) {
        if (
            calculatorMode == CalculatorMode.FixOperator
            || calculatorMode == CalculatorMode.InputClear
            || calculatorMode == CalculatorMode.Calculated
        ) {
            val latestHistory = calculationHistories.last()
            calculationHistories -= latestHistory
            calculationHistories += CalcEntity(
                number = latestHistory.number,
                operator = operator,
            )
        } else {
            this.result = calculationHistories.lastOrNull()?.let {
                try {
                    calculate(result, it.operator, input)
                } catch (e: ArithmeticException) {
                    calculatorMode = CalculatorMode.Error
                    Timber.e(e)
                    return
                }
            } ?: input
            calculationHistories += CalcEntity(
                number = input,
                operator = operator,
            )
        }
        calculatorMode = CalculatorMode.FixOperator
        initializeInputText()
    }

    private fun calculate(
        leftNumber: BigDecimal,
        operator: Controller.Operators,
        rightNumber: BigDecimal,
    ): BigDecimal {
        return when (operator) {
            Divide -> {
                leftNumber.divide(rightNumber, CalculationMathContext)
            }

            Times -> {
                leftNumber.multiply(rightNumber)
            }

            Minus -> {
                leftNumber.subtract(rightNumber)
            }

            Plus -> {
                leftNumber.add(rightNumber)
            }

            else -> {
                throw IllegalArgumentException("Invalid operator")
            }
        }
    }

    private fun onInputEqual() {
        val lastOfHistory = calculationHistories.lastOrNull() ?: return
        val rightNumber = when (calculatorMode) {
            CalculatorMode.InputtingNumber -> inputText.toBigDecimal()
            CalculatorMode.FixOperator, CalculatorMode.Calculated -> lastOfHistory.number
            else -> return
        }
        calculationHistories += CalcEntity(
            number = rightNumber,
            operator = lastOfHistory.operator,
        )
        result = try {
            calculate(result, lastOfHistory.operator, rightNumber)
        } catch (e: ArithmeticException) {
            calculatorMode = CalculatorMode.Error
            Timber.e(e)
            return
        }
        calculatorMode = CalculatorMode.Calculated
        initializeInputText()
    }

    private fun onInputClear() {
        calculatorMode = CalculatorMode.InputClear
        initializeInputText()
    }

    private fun onInputAllClear() {
        allClear()
    }

    private fun onInputSwitch() {
        inputText = inputText.toBigDecimal().negate().toString()
    }

    private fun onInputPercent() {
        inputText = result
            .multiply(inputText.toBigDecimal())
            .divide(100.toBigDecimal(), CalculationMathContext)
            .toString()
    }

    private fun initializeInputText() {
        inputText = "0"
    }

    private fun allClear() {
        inputText = "0"
        clearHistoryAndResult()
        calculatorMode = CalculatorMode.IdleInput
    }

    private fun clearHistoryAndResult() {
        result = BigDecimal.ZERO
        calculationHistories = emptyList()
    }

    private fun shouldBeInputText(
        number: Controller.Numbers
    ): Boolean {
        return (number == Controller.Numbers.Dot && inputText.contains(number.text))
                || (calculatorMode == CalculatorMode.Error)
                || (inputText.replace(".", "").length >= MaxInputNumberSize)
    }

    enum class CalculatorMode {
        IdleInput, InputtingNumber, FixOperator, InputClear, Calculated, Error,
    }
}