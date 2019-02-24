package com.example.yosuke.calculator.view.buttons

import com.example.yosuke.calculator.R

enum class Operators(override val stringRecourse: Int) : CalcButton {
    PLUS(R.string.plus),
    MINUS(R.string.minus),
    TIMES(R.string.times),
    DIVIDE(R.string.divide);
}