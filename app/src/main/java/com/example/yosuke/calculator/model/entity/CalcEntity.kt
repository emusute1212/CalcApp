package com.example.yosuke.calculator.model.entity

import java.math.BigDecimal

data class CalcEntity(val number: BigDecimal, val operator: Operators) {
    override fun toString(): String {
        return "$number ${operator.toStr()}"
    }
}