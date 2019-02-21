package com.example.yosuke.calculator

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class CalcViewModel @Inject constructor() : ViewModel() {
    val number = MutableLiveData<String>()

    fun onClickNumberButton(input: String) {
        //小数点が入力された時に、すでに少数になっているときは早期リターン
        if (input == "." && number.value?.contains(".") == true) return

        val tempNumber: String = (number.value ?: "") + input
        number.postValue(tempNumber)
    }
}