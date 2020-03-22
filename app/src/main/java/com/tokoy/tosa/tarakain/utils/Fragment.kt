package com.tokoy.tosa.tarakain.utils

import android.app.Activity
import android.graphics.Color
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.util.*

fun Fragment.hideKeyboard() {
    val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Fragment.getRandomColor(): Int {
    val random = Random()
    return Color.argb(
        255,
        random.nextInt(256),
        random.nextInt(256),
        random.nextInt(256)
    )
}