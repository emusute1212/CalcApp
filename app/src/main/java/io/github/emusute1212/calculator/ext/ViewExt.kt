package io.github.emusute1212.calculator.ext

import android.view.HapticFeedbackConstants
import android.view.View
import androidx.databinding.BindingAdapter

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