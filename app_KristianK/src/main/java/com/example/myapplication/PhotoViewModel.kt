package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.photosListRelated.Photo
import kotlinx.coroutines.launch
import org.json.JSONObject

class PhotoViewModel(val currentPhoto: Photo) : ViewModel() {
    fun setTitle(newTitle: String) {
        viewModelScope.launch {
            val newPhoto = Photo(currentPhoto.albumId, currentPhoto.id, currentPhoto.thumbnailUrl, newTitle, currentPhoto.url)
            val d = JsonApi.retrofitService.putPhoto(currentPhoto.id, newPhoto)
            Log.d("krisa", d.toString())
            Log.d("krisa", d.body().toString())
        }
    }
    fun delete(){
        viewModelScope.launch {
            val d = JsonApi.retrofitService.deletePhoto(currentPhoto.id)
            if (d != null) {
                Log.d("krisa", d.toString())
            }
        }
    }
}