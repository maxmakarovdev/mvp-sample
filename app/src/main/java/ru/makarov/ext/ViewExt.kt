package ru.makarov.ext

import android.view.View

fun View.onClick(action: () -> Unit) {
    setOnClickListener { action.invoke() }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}