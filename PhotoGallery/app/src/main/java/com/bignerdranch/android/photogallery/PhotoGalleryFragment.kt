package com.bignerdranch.android.photogallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.photogallery.api.FlickrApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "PhotoGalleryFragment"

class PhotoGalleryFragment : Fragment(){
    private lateinit var photoGalleryViewModel: PhotoGalleryViewModel
    private lateinit var photoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

/*        val flickrHomePageRequest: Call<String> = flickrApi.fetchContents()

        // Call 객체가 나타내는 웹 요청을 백그라운드 스레드에서 실행
        // 큐로 관리하며, Retrofit은 큐가 비워질 때까지 하나씩 요청을 처리한다.
        flickrHomePageRequest.enqueue(object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable){
                Log.e(TAG, "Failed to fetch Photos", t)
            }

            // Response 객체는 자신의 몸체에 결과 콘텐츠를 포함
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ){
                Log.d(TAG, "Response received: ${response.body()}")
            }
            
            // 안드로이드는 main 스레드에서의 네트워크 작업 수행을 허용하지 않음
            // NetworkOnMainThreadException
        })*/
        
/*        // FlickrFetchr는 PhotoGallery 앱의 네트워크 관련 코드를 가짐
        // fetchContents는 네트워크 요청을 큐에 넣고 응답 결과를 LiveData로 반환
        val flickrLiveData: LiveData<List<GalleryItem>> = FlickrFetchr().fetchPhotos()
        flickrLiveData.observe(
            this,
            Observer { galleryItems ->
                Log.d(TAG, "Response Received: $galleryItems")
            }
        )*/
        photoGalleryViewModel = ViewModelProvider(this).get(PhotoGalleryViewModel::class.java)
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

    private class PhotoHolder(itemTextView: TextView): RecyclerView.ViewHolder(itemTextView){
        val bindTitle: (CharSequence) -> Unit = itemTextView::setText
    }

    private class PhotoAdapter(private val galleryItems: List<GalleryItem>): RecyclerView.Adapter<PhotoHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
            val textView = TextView(parent.context)
            return PhotoHolder(textView)
        }

        override fun getItemCount(): Int = galleryItems.size

        override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
            val galleryItem = galleryItems[position]
            holder.bindTitle(galleryItem.title)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoGalleryViewModel.galleryItemLiveData.observe(
            viewLifecycleOwner,
            Observer { galleryItems ->
                photoRecyclerView.adapter = PhotoAdapter(galleryItems)
            }
        )
    }
}