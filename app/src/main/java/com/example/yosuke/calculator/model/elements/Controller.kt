package com.example.yosuke.calculator.model.elements

interface Controller

enum class Operators : Controller {
    PLUS,
    MINUS,
    TIMES,
    DIVIDE,
    EQUAL;
}

enum class Specials : Controller {
    CLEAR,
    SWITCH,
    PERCENT;
}