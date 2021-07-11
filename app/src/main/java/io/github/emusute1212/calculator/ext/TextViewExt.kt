package io.github.emusute1212.calculator.ext

import android.graphics.Typeface
import android.widget.TextView
import androidx.databinding.BindingAdapter
import io.github.emusute1212.calculator.model.entity.CalcEntity
import io.github.emusute1212.calculator.model.entity.toStr
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