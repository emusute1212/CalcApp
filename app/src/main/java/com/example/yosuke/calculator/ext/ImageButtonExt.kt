package com.example.yosuke.calculator.ext

import android.databinding.BindingAdapter
import android.support.annotation.DrawableRes
import android.widget.ImageButton

@BindingAdapter("android:setDrawable")
fun ImageButton.setDrawable(@DrawableRes resource: Int) {
    this.setImageResource(resource)
}