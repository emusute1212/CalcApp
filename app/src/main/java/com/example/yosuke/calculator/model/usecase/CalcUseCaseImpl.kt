package com.example.yosuke.calculator.model.usecase

import com.example.yosuke.calculator.model.entity.Operators
import java.math.BigDecimal
import java.math.MathContext

class CalcUseCaseImpl : CalcUseCase {
    @Throws(ArithmeticException::class)
    override fun calc(x: BigDecimal, operator: Operators, y: BigDecimal): BigDecimal {
        return when (operator) {
            Operators.PLUS -> x.add(y, MathContext.DECIMAL32)
            Operators.MINUS -> x.subtract(y, MathContext.DECIMAL32)
            Operators.TIMES -> x.multiply(y, MathContext.DECIMAL32)
            Operators.DIVIDE -> x.divide(y, MathContext.DECIMAL32)
            Operators.EQUAL -> x
        }
    }
}