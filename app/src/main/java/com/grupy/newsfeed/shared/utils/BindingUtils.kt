package com.grupy.newsfeed.shared.utils

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("visibleIf")
fun visibleIf(anyView: View, show: Boolean) {
    anyView.visibility = if (show) View.VISIBLE else View.GONE
}
