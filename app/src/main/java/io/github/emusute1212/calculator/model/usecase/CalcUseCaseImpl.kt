package io.github.emusute1212.calculator.model.usecase

import io.github.emusute1212.calculator.model.entity.Controller
import java.math.BigDecimal
import java.math.MathContext

class CalcUseCaseImpl : CalcUseCase {
    @Throws(ArithmeticException::class)
    override fun calc(x: BigDecimal, operator: Controller.Operators, y: BigDecimal): BigDecimal {
        return when (operator) {
            Controller.Operators.PLUS -> x.add(y, MathContext.DECIMAL32)
            Controller.Operators.MINUS -> x.subtract(y, MathContext.DECIMAL32)
            Controller.Operators.TIMES -> x.multiply(y, MathContext.DECIMAL32)
            Controller.Operators.DIVIDE -> x.divide(y, MathContext.DECIMAL32)
            Controller.Operators.EQUAL -> x
        }
    }
}