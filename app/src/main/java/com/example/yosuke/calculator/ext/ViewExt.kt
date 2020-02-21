package com.example.yosuke.calculator.ext

import android.databinding.BindingAdapter
import android.view.HapticFeedbackConstants
import android.view.View

@BindingAdapter("android:onFeedbackClick")
fun View.setOnFeedbackClick(listener: View.OnClickListener) {
    setOnClickListener {
        it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        listener.onClick(it)
    }
}

fun View.setOnFeedbackClick(listener: (View) -> Unit) {
    setOnClickListener {
        it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        listener(it)
    }
}