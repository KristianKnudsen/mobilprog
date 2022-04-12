package com.example.myapplication.albumsRelated

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val id: Int,
    val title: String,
    val userId: Int
) : Parcelable