package com.example.yosuke.calculator.ext

import android.databinding.BindingAdapter
import android.widget.TextView
import com.example.yosuke.calculator.model.entity.CalcEntity
import com.example.yosuke.calculator.model.entity.toStr
import android.graphics.Typeface



@BindingAdapter("android:showCalcList")
fun TextView.showCalcList(progress: List<CalcEntity>) {
    text = progress.joinToString("") { it.number.toString() + it.operator.toStr() }
}

@BindingAdapter("android:setFont")
fun TextView.setFont(path: String) {
    typeface = Typeface.createFromAsset(context.assets, path)
}