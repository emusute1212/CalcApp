package com.example.yosuke.calculator.view.buttons

interface CalcButton

enum class Operators : CalcButton {
    PLUS,
    MINUS,
    TIMES,
    DIVIDE,
    EQUAL;
}

enum class Specials : CalcButton {
    CLEAR,
    SWITCH,
    PERCENT;
}