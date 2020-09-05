package com.tokoy.tosa.tarakain.adapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import java.lang.NumberFormatException

@BindingAdapter("android:text")
fun setInt(textview: TextView, value: Int?) {
    if (value != null) {
        textview.text = value.toString()
    } else {
        textview.text = null
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun getInt(textview: TextView): Int? {
    val value = textview.text?.toString()
    if (value.isNullOrEmpty()) {
        return null
    }
    return try {
        value.toInt()
    } catch (exception: NumberFormatException) {
        null
    }
}

@BindingAdapter("isGone")
fun setIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}