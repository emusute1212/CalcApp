package com.example.yosuke.calculator.model.usecase

import com.example.yosuke.calculator.model.entity.Operators
import java.math.BigDecimal

class CalcUseCaseImpl : CalcUseCase {
    override fun calc(x: BigDecimal, operator: Operators, y: BigDecimal): BigDecimal {
        return when (operator) {
            Operators.PLUS -> x + y
            Operators.MINUS -> x - y
            Operators.TIMES -> x * y
            Operators.DIVIDE -> x / y
            Operators.EQUAL -> x
        }
    }
}