package com.example.yosuke.calculator.model.entity

data class CalcEntity(val number: Double, var operator: Operators) {
    override fun toString(): String {
        return "$number ${operator.toStr()}"
    }
}