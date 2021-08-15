package io.github.emusute1212.calculator.model.usecase

import io.github.emusute1212.calculator.model.entity.Controller
import java.math.BigDecimal

interface CalcUseCase {
    fun calc(x: BigDecimal, operator: Controller.Operators, y: BigDecimal): BigDecimal
}