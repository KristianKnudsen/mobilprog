package com.example.myapplication.userListRelated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.PhotoViewModel
import com.example.myapplication.photosListRelated.Photo
import com.example.myapplication.room.UserRepo

class UsersViewModelFactory(private val userRepo: UserRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(userRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}