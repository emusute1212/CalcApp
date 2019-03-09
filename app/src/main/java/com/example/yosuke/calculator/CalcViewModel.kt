package com.example.yosuke.calculator

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.StringRes
import com.example.yosuke.calculator.view.buttons.CalcButton
import com.example.yosuke.calculator.view.buttons.Operators
import com.example.yosuke.calculator.view.buttons.Specials
import javax.inject.Inject

class CalcViewModel @Inject constructor() : ViewModel() {
    val number = MutableLiveData<String>()

    fun onClickNumberButton(input: String) {
        //小数点が入力された時に、すでに少数になっているときは早期リターン
        if (input == "." && number.value?.contains(".") == true) return

        val tempNumber: String = (number.value ?: "") + input
        number.postValue(tempNumber)
    }

    @StringRes
    fun getButtonTextRes(button: CalcButton): Int {
        return when (button) {
            Specials.CLEAR -> {
                if (number.value.isNullOrEmpty()) {
                    R.string.all_clear
                } else {
                    R.string.clear
                }
            }
            Specials.PERCENT -> R.string.percent
            Specials.SWITCH -> R.string.plus_minus_switch
            Operators.PLUS -> R.string.plus
            Operators.MINUS -> R.string.minus
            Operators.TIMES -> R.string.times
            Operators.DIVIDE -> R.string.divide
            else -> -1
        }
    }
}