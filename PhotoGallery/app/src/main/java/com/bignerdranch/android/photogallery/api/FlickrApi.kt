package com.bignerdranch.android.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {
    @GET("/")
    
    // String으로 지정했으므로, HTTP 응답이 String 객체로 역직렬화
    fun fetchContents(): Call<String>
}