package com.example.yosuke.calculator.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.support.annotation.StringRes
import android.util.Log
import com.example.yosuke.calculator.R
import com.example.yosuke.calculator.model.entity.CalcEntity
import com.example.yosuke.calculator.model.entity.Controller
import com.example.yosuke.calculator.model.entity.Operators
import com.example.yosuke.calculator.model.entity.Specials
import com.example.yosuke.calculator.model.usecase.CalcUseCase
import javax.inject.Inject

class CalcViewModel @Inject constructor(
    private val useCase: CalcUseCase
) : ViewModel() {
    val number = MutableLiveData<String>()
    val result = MutableLiveData<Long>()
    val calcProgress = ObservableArrayList<CalcEntity>()
    private var lastCalcData: Pair<Operators?, Long?> = null to null
    private val numberTypeOfLong: Long
        get() = number.value?.toLong() ?: 0
    private val resultTypeOfLong: Long
        get() = result.value?.toLong() ?: 0
    private var isCalc = false

    fun onClickNumberButton(input: String) {
        //小数点が入力された時に、すでに少数になっているときは早期リターン
        if (input == "." && number.value?.contains(".") == true) return

        val tempNumber: String = (number.value ?: "") + input
        number.postValue(tempNumber)
        isCalc = false
    }

    @StringRes
    fun getButtonTextRes(button: Controller): Int {
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
            Operators.EQUAL -> R.string.equal
            else -> -1
        }
    }

    fun onClickOperatorButton(operators: Operators) {
        Log.d("dasdjkl", result.value.toString())
        Log.d("dasdjkl", lastCalcData.toString())
        //計算済みの時
        if (!isCalc) {
            calcProgress.add(CalcEntity(numberTypeOfLong, operators))
            result.value = if (lastCalcData.first == null || lastCalcData.second == null) {
                numberTypeOfLong
            } else {
                useCase.calc(resultTypeOfLong, lastCalcData.first!!, numberTypeOfLong)
            }
            lastCalcData = operators to numberTypeOfLong
            number.value = null
            isCalc = true
        } else {
            calcProgress.last().operator = operators
            lastCalcData = operators to lastCalcData.second
        }
        Log.d("dakldj", result.value.toString())
    }
}