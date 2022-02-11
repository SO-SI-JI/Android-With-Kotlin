package com.bignerdranch.android.photogallery.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface FlickrApi {
    /*
        @GET("/")
        // String으로 지정했으므로, HTTP 응답이 String 객체로 역직렬화
        fun fetchContents(): Call<String>
    */
    @GET("services/rest?method=flickr.interestingness.getList"
    )
    fun fetchPhotos(): Call<FlickrResponse>

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>

    
    //파라미터 동적으로 추가
    @GET("services/rest?method=flickr.photos.search")
    fun searchPhotos(@Query("text") query: String): Call<FlickrResponse>
}