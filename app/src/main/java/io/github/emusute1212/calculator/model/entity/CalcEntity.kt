package io.github.emusute1212.calculator.model.entity

import java.math.BigDecimal

data class CalcEntity(val number: BigDecimal, val operator: Controller.Operators) {
    override fun toString(): String {
        return "$number ${operator.text}"
    }
}