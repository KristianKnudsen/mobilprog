package com.example.myapplication.albumsRelated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.user.User

class AlbumsViewModelFactory(val currentUser: User): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumsViewModel::class.java)) {
            return AlbumsViewModel(currentUser) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}