package io.github.emusute1212.calculator.extensitons

import java.text.NumberFormat
import java.util.Locale

fun String.toFormattedNumber(): String {
    if (isNumber().not()) return this
    return NumberFormat
        .getNumberInstance(Locale.getDefault())
        .format(toBigDecimal())
}

fun String.isNumber(): Boolean {
    // オーバーフローチェックは難しいので例外チェックで求める
    return try {
        toLong()
        true
    } catch (e: NumberFormatException) {
        false
    }
}
