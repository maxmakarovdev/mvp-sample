package ru.makarov.ext

import android.content.Context
import android.widget.Toast

fun Context.toast(messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(messageResId), duration)
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}