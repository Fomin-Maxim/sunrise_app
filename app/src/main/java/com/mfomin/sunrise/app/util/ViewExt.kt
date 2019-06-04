package com.mfomin.sunrise.app.util

import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver

fun View.convertDpToPx(dp: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    ).toInt()
}

fun View.removeGlobalLayoutListener(listener: ViewTreeObserver.OnGlobalLayoutListener) {
    this.viewTreeObserver.removeOnGlobalLayoutListener(listener)
}

fun View.setVisibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}