package com.example.yosuke.calculator.model.usecase

import com.example.yosuke.calculator.model.entity.Operators
import java.math.BigDecimal

interface CalcUseCase {
    fun calc(x: BigDecimal, operator: Operators, y: BigDecimal): BigDecimal
}