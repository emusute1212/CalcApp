package com.example.yosuke.calculator.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.support.annotation.StringRes
import com.example.yosuke.calculator.R
import com.example.yosuke.calculator.model.entity.CalcEntity
import com.example.yosuke.calculator.model.entity.Controller
import com.example.yosuke.calculator.model.entity.Operators
import com.example.yosuke.calculator.model.entity.Specials
import com.example.yosuke.calculator.model.usecase.CalcUseCase
import java.math.BigDecimal
import javax.inject.Inject

class CalcViewModel @Inject constructor(
    private val useCase: CalcUseCase
) : ViewModel() {
    val calcProgress = ObservableArrayList<CalcEntity>()
    val number = MutableLiveData<String>()
    private val inputNumber = MutableLiveData<String>()
    private val result = MutableLiveData<String>()
    private var lastOperator: Operators? = null
    private var lastNumber: BigDecimal? = null
    private val numberTypeOfBigDecimal: BigDecimal
        get() = number.value?.toBigDecimal() ?: 0.toBigDecimal()
    private val resultTypeOfBigDecimal: BigDecimal
        get() = result.value?.toBigDecimal() ?: 0.toBigDecimal()
    private val inputNumberTypeOfBigDecimal: BigDecimal
        get() = inputNumber.value?.toBigDecimal() ?: 0.toBigDecimal()
    private var isCalc = false

    fun onClickNumberButton(input: String) {
        //小数点が入力された時に、すでに少数になっているときは早期リターン
        if (input == "." && inputNumber.value?.contains(".") == true) return
        //初回に0を入力したときは早期リターン
        if (input == "0" && inputNumber.value == null) return
        //初回に.を入力したときは0を挿入する
        if (input == "." && inputNumber.value.isNullOrEmpty()) number.value = "0"


        val tempNumber: String = (inputNumber.value ?: "") + input
        inputNumber.value = tempNumber
        isCalc = false
        number.value = inputNumber.value
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
        //計算済みの時
        if (!isCalc) {
            lastNumber = inputNumberTypeOfBigDecimal
            if (operators == Operators.EQUAL) {
                inputEqual()
                return
            }
            calcProgress.add(CalcEntity(inputNumberTypeOfBigDecimal, operators))
            result.value = if (lastOperator == null) {
                inputNumber.value
            } else {
                useCase.calc(resultTypeOfBigDecimal, requireNotNull(lastOperator), requireNotNull(lastNumber))
                    .toString()
            }
            lastOperator = operators
            inputNumber.value = null
            isCalc = true
        } else {
            calcProgress.last().operator = operators
            lastOperator = operators
        }
        number.value = result.value
    }

    private fun inputEqual() {
        result.value =
            useCase.calc(resultTypeOfBigDecimal, requireNotNull(lastOperator), requireNotNull(lastNumber)).toString()
        number.value = result.value
    }
}