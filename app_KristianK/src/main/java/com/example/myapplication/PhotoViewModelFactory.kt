package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.albumsRelated.Album
import com.example.myapplication.photosListRelated.Photo
import com.example.myapplication.photosListRelated.PhotosViewModel

class PhotoViewModelFactory(val currentPhoto: Photo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java)) {
            return PhotoViewModel(currentPhoto) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}