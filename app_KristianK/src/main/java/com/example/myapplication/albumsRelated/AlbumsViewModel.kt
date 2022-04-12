package com.example.myapplication.albumsRelated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.JsonApi
import com.example.myapplication.user.User
import kotlinx.coroutines.launch

class AlbumsViewModel(val currentUser: User): ViewModel() {
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    private val _albumsLoaded = MutableLiveData(false)
    val albumsLoaded: LiveData<Boolean>
        get() = _albumsLoaded

    init {
        getAllAlbums()
    }

    private fun getAllAlbums() {
        viewModelScope.launch {
            _albums.value = JsonApi.retrofitService.getAlbumsForUser(currentUser.id)
            _albumsLoaded.value = true
        }
    }
}
