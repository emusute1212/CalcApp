package io.github.emusute1212.calculator.model.usecase

import io.github.emusute1212.calculator.model.entity.Operators
import java.math.BigDecimal

interface CalcUseCase {
    fun calc(x: BigDecimal, operator: Operators, y: BigDecimal): BigDecimal
}