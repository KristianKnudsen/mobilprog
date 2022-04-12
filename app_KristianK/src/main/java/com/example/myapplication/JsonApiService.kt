package com.example.myapplication

import com.example.myapplication.albumsRelated.Album
import com.example.myapplication.photosListRelated.Photo
import com.example.myapplication.user.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


interface JsonApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
    @GET("albums")
    suspend fun getAlbumsForUser(@Query("userId") type: Int): List<Album>
    @GET("photos")
    suspend fun getPhotosForAlbum(@Query("albumId") type: Int): List<Photo>
    @PUT("photos/{id}")
    suspend fun putPhoto(@Path("id") type: Int, @Body photo: Photo): Response<Photo>
    @DELETE("photos/{id}")
    suspend fun deletePhoto(@Path("id") type: Int): Response<*>?

}

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object JsonApi{
    val retrofitService : JsonApiService by lazy {
        retrofit.create(JsonApiService::class.java)
    }
}