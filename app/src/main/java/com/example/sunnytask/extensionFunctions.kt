package com.example.sunnytask

import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar


fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

fun EditText.togglePasswordVisibility(showPassword: Boolean) {
    transformationMethod = if (showPassword) null else PasswordTransformationMethod.getInstance()
    setSelection(length()) // Keep the cursor at the end
}