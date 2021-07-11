package io.github.emusute1212.calculator.ext

import android.widget.ImageButton
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

@BindingAdapter("android:setDrawable")
fun ImageButton.setDrawable(@DrawableRes resource: Int) {
    this.setImageResource(resource)
}