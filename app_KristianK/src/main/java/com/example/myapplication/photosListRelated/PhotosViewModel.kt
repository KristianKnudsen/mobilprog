package com.example.myapplication.photosListRelated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.JsonApi
import com.example.myapplication.albumsRelated.Album
import kotlinx.coroutines.launch

class PhotosViewModel(val currentAlbum: Album) : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _photosLoaded = MutableLiveData(false)
    val photosLoaded: LiveData<Boolean>
        get() = _photosLoaded

    init {
        getAllPhotos()
    }

    private fun getAllPhotos() {
        viewModelScope.launch {
            _photos.value = JsonApi.retrofitService.getPhotosForAlbum(currentAlbum.id)
            _photosLoaded.value = true
        }
    }
}