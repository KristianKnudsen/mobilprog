package com.example.myapplication

import android.content.Context
import com.example.myapplication.room.UserDatabase
import com.example.myapplication.room.UserRepo

object ServiceLocator {
    var userRepository: UserRepo? = null

    fun provideUserRepository(context: Context): UserRepo {
        synchronized(this){
            return userRepository ?: createUserRepository(context)
        }
    }

    private fun createUserRepository(context: Context): UserRepo {
        val userDao = UserDatabase.getDatabase(context).userDao()
        userRepository = UserRepo(userDao)
        return userRepository as UserRepo
    }
}