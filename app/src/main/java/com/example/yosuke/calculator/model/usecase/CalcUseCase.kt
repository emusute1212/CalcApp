package com.example.yosuke.calculator.model.usecase

import com.example.yosuke.calculator.model.elements.Operators

interface CalcUseCase {
    fun calc(x: Long, operator: Operators, y: Long): Long
}