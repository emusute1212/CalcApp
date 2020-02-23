package com.example.yosuke.calculator.ext

import android.databinding.BindingAdapter
import android.graphics.Typeface
import android.widget.TextView
import com.example.yosuke.calculator.model.entity.CalcEntity
import com.example.yosuke.calculator.model.entity.toStr
import java.text.NumberFormat
import java.util.*


@BindingAdapter("android:showCalcList")
fun TextView.showCalcList(progress: List<CalcEntity>) {
    text = progress.joinToString("") { it.number.toString() + it.operator.toStr() }
}

@BindingAdapter("android:setFont")
fun TextView.setFont(path: String) {
    typeface = Typeface.createFromAsset(context.assets, path)
}

@BindingAdapter("android:setNumberText")
fun TextView.setNumberText(target: String) {
    text = if (target.isNumber()) {
        NumberFormat.getNumberInstance(Locale.getDefault()).format(target.toBigDecimal())
    } else {
        target
    }
}