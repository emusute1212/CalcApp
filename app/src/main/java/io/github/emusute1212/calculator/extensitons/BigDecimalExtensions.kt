package io.github.emusute1212.calculator.extensitons

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

const val MaxInputNumberSize = 9
val CalculationMathContext = MathContext(MaxInputNumberSize, RoundingMode.HALF_EVEN)

fun BigDecimal.toStringWithMathContext(
    mathContext: MathContext,
): String {
    return toPlainString().toBigDecimal(mathContext).toString()
}
