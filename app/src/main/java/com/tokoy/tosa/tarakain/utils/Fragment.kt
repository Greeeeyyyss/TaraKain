package com.tokoy.tosa.tarakain.utils

import android.app.Activity
import android.graphics.Color
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.tokoy.tosa.tarakain.R
import java.util.Random

fun Fragment.hideKeyboard() {
    val inputMethodManager =
        context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
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

fun Fragment.showSnackbar(message: String) {
    val context = context ?: return
    val view = this.view ?: return
    val snackbar = Snackbar.make(
        view,
        message,
        Snackbar.LENGTH_SHORT
    )
    snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.colorPrimaryDark))
    snackbar.show()
}