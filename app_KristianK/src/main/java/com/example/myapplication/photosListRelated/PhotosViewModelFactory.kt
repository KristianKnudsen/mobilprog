package com.example.myapplication.photosListRelated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.albumsRelated.Album

class PhotosViewModelFactory(val currentAlbum: Album): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
            return PhotosViewModel(currentAlbum) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}