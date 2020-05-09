package io.github.emusute1212.calculator.ext

fun String.isNumber(): Boolean {
    // オーバーフローチェックは難しいので例外チェックで求める
    return try {
        toLong()
        true
    } catch (e: NumberFormatException) {
        false
    }
}