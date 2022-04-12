package com.example.myapplication.userListRelated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.user.User
import com.example.myapplication.JsonApi
import com.example.myapplication.room.UserEntity
import com.example.myapplication.room.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel(private val repository: UserRepo): ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private val _usersLoaded = MutableLiveData(false)
    val usersLoaded: LiveData<Boolean>
        get() = _usersLoaded

    init {
        getAllUsers()
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(UserEntity(1,"name axa", "username exe", "emai hehe"))
        }
    }

    private fun getAllUsers(){
        viewModelScope.launch {
            _users.value = repository.getAllUsers.value
            _usersLoaded.value = true
        }
    }
}