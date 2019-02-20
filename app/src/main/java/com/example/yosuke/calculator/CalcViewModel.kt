package com.example.yosuke.calculator

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class CalcViewModel : ViewModel() {
    val number = MutableLiveData<Double>().apply {
        postValue(0.0)
    }

    fun onClickNumberButton(input: String) {
        val checkNum: Double = (number.value.toString() + input).toDoubleOrNull() ?: return

        number.postValue(checkNum)
    }
}