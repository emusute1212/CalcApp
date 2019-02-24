package com.example.yosuke.calculator.ext

import android.databinding.BindingAdapter
import android.support.annotation.StringRes
import android.widget.TextView

@BindingAdapter("android:setTextByRes")
fun TextView.setTextByRes(@StringRes stringRes: Int) {
    setText(stringRes)
}