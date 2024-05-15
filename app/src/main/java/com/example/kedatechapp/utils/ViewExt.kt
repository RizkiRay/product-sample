package com.example.kedatechapp.utils

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import com.example.kedatechapp.R


fun Activity.getStatusBarHeight(): Int {
    val rectangle = Rect()
    this.window.decorView.getWindowVisibleDisplayFrame(rectangle)
    return rectangle.top
}

fun Activity.setTranslucentStatusBar() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.statusBarColor = ContextCompat.getColor(this, R.color.opaque)
}

fun Activity.setColoredStatusBar() {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    window.statusBarColor = ContextCompat.getColor(this, R.color.purple_700)
}

fun View.addMargin(left: Int, top: Int, right: Int, bottom: Int) {
    val params = this.layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(left, top, right, bottom)
    this.layoutParams = params
    this.requestLayout()
}

fun ImageView.load(url: String) {
    Glide.with(this).load(url).centerCrop().into(this);
}