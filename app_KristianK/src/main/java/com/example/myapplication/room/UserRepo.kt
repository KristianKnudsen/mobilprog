package com.example.myapplication.room

import androidx.lifecycle.LiveData
import com.example.myapplication.JsonApi
import com.example.myapplication.user.User

class UserRepo(private val userDao: UserDao) {

    val getAllUsers: LiveData<List<UserEntity>> = userDao.getAllUsers()

    suspend fun addUser(user: UserEntity){
        userDao.addUser(user)
    }

    suspend fun fetchAllUsers(): List<User> {
        return JsonApi.retrofitService.getUsers()
    }
}