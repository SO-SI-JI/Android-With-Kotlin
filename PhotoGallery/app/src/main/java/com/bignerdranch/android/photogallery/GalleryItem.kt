package com.bignerdranch.android.photogallery

import com.google.gson.annotations.SerializedName

data class GalleryItem(
            var title: String = "",
            var id: String = "",

            // JSON 객체의 필드 이름과 다르면 @SerializedName
            @SerializedName("url_s") var url: String = ""
)