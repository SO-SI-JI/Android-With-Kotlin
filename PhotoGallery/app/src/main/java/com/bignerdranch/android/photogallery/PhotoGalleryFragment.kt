package com.bignerdranch.android.photogallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.photogallery.api.FlickrApi
import retrofit2.Retrofit

class PhotoGalleryFragment : Fragment(){
    private lateinit var photoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/")
            .build()

        val flickrApi : FlickrApi = retrofit.create(FlickrApi::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_photo_gallery, container, false)

        photoRecyclerView = view.findViewById(R.id.photo_recycler_view)
        
        // 레이아웃 매니저를 GridLayoutManager로 설정
        // 격자 개수로 3을 하드코딩
        // 화면 너비에 맞게 동적으로 조정하는 방법은 과제로 남겨두었다는데 답이 뭘까요
        photoRecyclerView.layoutManager = GridLayoutManager(context, 3)

        return view
    }

    companion object{
        fun newInstance() = PhotoGalleryFragment()
    }
}