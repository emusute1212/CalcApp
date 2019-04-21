package com.example.yosuke.calculator.ext

import java.math.BigDecimal

fun BigDecimal.percent(pct: BigDecimal): BigDecimal {
    return multiply(pct).divide(BigDecimal(100))
}