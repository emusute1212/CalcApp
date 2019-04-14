package com.example.yosuke.calculator.ext

import android.databinding.BindingAdapter
import android.support.annotation.StringRes
import android.widget.TextView
import com.example.yosuke.calculator.model.entity.CalcEntity
import com.example.yosuke.calculator.model.entity.toStr

@BindingAdapter("android:setTextByRes")
fun TextView.setTextByRes(@StringRes stringRes: Int) {
    setText(stringRes)
}

@BindingAdapter("android:showCalcList")
fun TextView.showCalcList(progress: List<CalcEntity>) {
    text = progress.joinToString("") { it.number.toString() + it.operator.toStr() }
}