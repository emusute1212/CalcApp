package com.example.yosuke.calculator.model.entity

data class CalcEntity(val number: Long, val operator: Operators) {
    override fun toString(): String {
        return "$number ${operator.toStr()}"
    }
}