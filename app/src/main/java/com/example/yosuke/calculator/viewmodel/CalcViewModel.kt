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
    val result = MutableLiveData<Double>()
    val calcProgress = ObservableArrayList<CalcEntity>()
    private var lastCalcData: Pair<Operators?, Double?> = null to null
    private val numberTypeOfDouble: Double
        get() = number.value?.toDouble() ?: 0.toDouble()
    private val resultTypeOfDouble: Double
        get() = result.value?.toDouble() ?: 0.toDouble()
    private var isCalc = false

    fun onClickNumberButton(input: String) {
        //小数点が入力された時に、すでに少数になっているときは早期リターン
        if (input == "." && number.value?.contains(".") == true) return
        //初回に0を入力したときは早期リターン
        if (input == "0" && number.value == null) return
        //初回に.を入力したときは0を挿入する
        if (input == "." && number.value.isNullOrEmpty()) number.value = "0"

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
            calcProgress.add(CalcEntity(numberTypeOfDouble, operators))
            result.value = if (lastCalcData.first == null || lastCalcData.second == null) {
                numberTypeOfDouble
            } else {
                useCase.calc(resultTypeOfDouble, lastCalcData.first!!, numberTypeOfDouble)
            }
            lastCalcData = operators to numberTypeOfDouble
            number.value = null
            isCalc = true
        } else {
            calcProgress.last().operator = operators
            lastCalcData = operators to lastCalcData.second
        }
        Log.d("dakldj", result.value.toString())
    }
}