package com.grupy.newsfeed.shared.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.app.ShareCompat

/**
 * Android system intents
 */

fun intentOpenWebsite(activity: Activity, url: String) {
    val openURL = Intent(Intent.ACTION_VIEW)
    openURL.data = Uri.parse(url)
    activity.startActivity(openURL)
}
