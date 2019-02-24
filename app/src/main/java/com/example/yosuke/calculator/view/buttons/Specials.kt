package com.example.yosuke.calculator.view.buttons

import com.example.yosuke.calculator.R

enum class Specials(override val stringRecourse: Int) : CalcButton {
    ALL_CLEAR(R.string.all_clear),
    CLEAR(R.string.clear),
    SWITCH(R.string.plus_minus_switch),
    PERCENT(R.string.percent);
}