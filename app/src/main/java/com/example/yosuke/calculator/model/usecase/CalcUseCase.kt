package com.example.yosuke.calculator.model.usecase

import com.example.yosuke.calculator.model.entity.Operators

interface CalcUseCase {
    fun calc(x: Double, operator: Operators, y: Double): Double
}