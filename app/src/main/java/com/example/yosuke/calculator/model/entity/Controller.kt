package com.example.yosuke.calculator.model.entity

interface Controller

enum class Operators : Controller {
    PLUS,
    MINUS,
    TIMES,
    DIVIDE,
    EQUAL;
}

fun Operators.toStr(): String {
    return when (this) {
        Operators.PLUS -> "+"
        Operators.MINUS -> "-"
        Operators.TIMES -> "×"
        Operators.DIVIDE -> "÷"
        Operators.EQUAL -> "="
    }
}

enum class Specials : Controller {
    CLEAR,
    SWITCH,
    PERCENT;
}