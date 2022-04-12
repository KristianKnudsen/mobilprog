package com.example.myapplication.user

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
) : Parcelable