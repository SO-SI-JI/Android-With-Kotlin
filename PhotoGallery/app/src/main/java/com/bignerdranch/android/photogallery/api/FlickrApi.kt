package com.bignerdranch.android.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {
    /*
        @GET("/")
        // String으로 지정했으므로, HTTP 응답이 String 객체로 역직렬화
        fun fetchContents(): Call<String>
    */
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=7dae9721c3af0a9ccd511215078c6bc8" +
                "&format=json" +
                "&nojsoncallback=1" +
                 "&extras=url_s"
    )
    fun fetchPhotos(): Call<String>
}