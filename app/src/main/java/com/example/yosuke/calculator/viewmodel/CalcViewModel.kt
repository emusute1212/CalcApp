package com.example.yosuke.calculator.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.support.annotation.DrawableRes
import android.util.Log
import com.example.yosuke.calculator.R
import com.example.yosuke.calculator.ext.percent
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
    private val resultTypeOfBigDecimal: BigDecimal
        get() = result.value?.toBigDecimal() ?: 0.toBigDecimal()
    private val inputNumberTypeOfBigDecimal: BigDecimal
        get() = inputNumber.value?.toBigDecimal() ?: 0.toBigDecimal()
    private var isCalc = false
    private var isFinish = false
    private val isAllClear
        get() = number.value.isNullOrEmpty()

    fun onClickNumberButton(input: String) {
        if (number.value == ERROR) return
        //小数点が入力された時に、すでに少数になっているときは早期リターン
        if (input == "." && inputNumber.value?.contains(".") == true) return
        //初回に0を入力したときは早期リターン
        if (input == "0" && inputNumber.value == null) {
            number.value = "0"
            return
        }
        //入力値がMAX_INPUT_NUMBER_SIZEを超えていたときは早期リターン
        if ((inputNumber.value?.replace(".", "")?.length ?: 0) >= MAX_INPUT_NUMBER_SIZE) return
        //初回に.を入力したときは0を挿入する
        if (input == "." && inputNumber.value.isNullOrEmpty()) inputNumber.value = "0"
        if (isFinish) {
            allClear()
        }

        val tempNumber: String = (inputNumber.value ?: "") + input
        inputNumber.value = tempNumber
        isCalc = false
        number.value = inputNumber.value
    }

    @DrawableRes
    fun getButtonImageRes(button: Controller): Int {
        return when (button) {
            Specials.CLEAR -> {
                if (isAllClear) {
                    R.drawable.all_clear
                } else {
                    R.drawable.clear
                }
            }
            Specials.PERCENT -> R.drawable.percent
            Specials.SWITCH -> R.drawable.plus_minus_switch
            Operators.PLUS -> R.drawable.plus
            Operators.MINUS -> R.drawable.minus
            Operators.TIMES -> R.drawable.times
            Operators.DIVIDE -> R.drawable.divide
            Operators.EQUAL -> R.drawable.equal
            else -> -1
        }
    }

    fun onClickOperatorButton(operators: Operators) {
        if (number.value == ERROR) return
        if (operators == Operators.EQUAL) {
            inputEqual()
            return
        }
        isFinish = false
        //計算済みの時
        if (!isCalc) {
            lastNumber = inputNumberTypeOfBigDecimal
            calcProgress.add(CalcEntity(inputNumberTypeOfBigDecimal, operators))
            result.value = if (lastOperator == null) {
                inputNumber.value
            } else {
                try {
                    useCase.calc(
                        resultTypeOfBigDecimal,
                        requireNotNull(lastOperator),
                        requireNotNull(lastNumber)
                    )
                        .toString()
                } catch (e: ArithmeticException) {
                    Log.w(TAG, "error", e)
                    ERROR
                }
            }
            lastOperator = operators
            inputNumber.value = null
            isCalc = true
        } else {
            calcProgress.last().let {
                val new = CalcEntity(it.number, operators)
                calcProgress.remove(calcProgress.last())
                calcProgress.add(new)
            }
            lastOperator = operators
        }
        number.value = result.value
    }

    fun onClickSpecialButton(special: Specials) {
        when (special) {
            Specials.CLEAR -> if (isAllClear) allClear() else clear()
            Specials.PERCENT -> percent()
            Specials.SWITCH -> minus()
        }
    }

    private fun allClear() {
        calcProgress.clear()
        result.value = null
        number.value = null
        isFinish = false
        lastNumber = null
        lastOperator = null
    }

    private fun clear() {
        number.value = null
        inputNumber.value = null
    }

    private fun percent() {
        if (number.value == ERROR) return
        inputNumber.value = resultTypeOfBigDecimal.percent(inputNumberTypeOfBigDecimal).toString()
        number.value = inputNumber.value
    }

    private fun minus() {
        if (number.value == ERROR) return
        inputNumber.value = (inputNumberTypeOfBigDecimal * (-1).toBigDecimal()).toString()
        number.value = inputNumber.value
    }

    private fun inputEqual() {
        if (!isFinish) {
            lastNumber = inputNumberTypeOfBigDecimal
        }
        inputNumber.value = null
        if (lastOperator == null) {
            number.value = lastNumber!!.toString()
            isFinish = true
            return
        }
        result.value = try {
            useCase.calc(
                resultTypeOfBigDecimal,
                requireNotNull(lastOperator),
                requireNotNull(lastNumber)
            ).toString()
        } catch (e: ArithmeticException) {
            Log.w(TAG, "error", e)
            ERROR
        }
        calcProgress.add(CalcEntity(requireNotNull(lastNumber), requireNotNull(lastOperator)))
        number.value = result.value
        isFinish = true
        isCalc = true
    }

    companion object {
        private val TAG = CalcViewModel::class.java.simpleName
        private const val MAX_INPUT_NUMBER_SIZE: Int = 9
        private const val ERROR = "エラー"
    }
}