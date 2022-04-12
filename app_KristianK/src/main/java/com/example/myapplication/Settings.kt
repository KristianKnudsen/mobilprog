package com.example.myapplication

import android.content.Context
import androidx.preference.PreferenceManager

class Settings(context: Context) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    val email: Boolean
        get() = prefs.getBoolean("email", false)

    val thumbnail: Boolean
        get() = prefs.getBoolean("thumbnail", false)
}