package com.tokoy.tosa.tarakain.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import java.lang.NumberFormatException

@BindingAdapter("android:text")
fun setInt(textview: TextView, value: Int?) {
    if (value != null) {
        textview.text = value.toString()
    } else {
        textview.text = "0"
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun getInt(textview: TextView): Int {
    val value = textview.text?.toString()
    if (value.isNullOrEmpty()) {
        return 0
    }
    return try {
        value.toInt()
    } catch (exception: NumberFormatException) {
        0
    }
}