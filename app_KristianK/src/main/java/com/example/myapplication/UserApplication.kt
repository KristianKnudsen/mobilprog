package com.example.myapplication

import android.app.Application
import com.example.myapplication.room.UserRepo

class UserApplication: Application() {
    val userRepository: UserRepo
        get() = ServiceLocator.provideUserRepository(this)
}