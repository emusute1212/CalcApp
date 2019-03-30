package com.example.yosuke.calculator.model.usecase

import com.example.yosuke.calculator.model.entity.Operators

class CalcUseCaseImpl : CalcUseCase {
    override fun calc(x: Long, operator: Operators, y: Long): Long {
        return when (operator) {
            Operators.PLUS -> x + y
            Operators.MINUS -> x - y
            Operators.TIMES -> x * y
            Operators.DIVIDE -> x / y
            Operators.EQUAL -> x
        }
    }
}