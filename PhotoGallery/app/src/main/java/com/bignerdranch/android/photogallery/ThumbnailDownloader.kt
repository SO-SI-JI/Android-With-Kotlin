package com.bignerdranch.android.photogallery

import android.annotation.SuppressLint
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.util.concurrent.ConcurrentHashMap

private const val TAG = "ThumbnailDownloader"
private const val MESSAGE_DOWNLOAD = 0

class ThumbnailDownloader<in T> : HandlerThread(TAG), LifecycleObserver {
    private var hasQuit = false
    private lateinit var requestHandler: Handler
    private val requestMap = ConcurrentHashMap<T, String>()
    private val flickrFetchr = FlickrFetchr()

    //메시ㅣ지 처리
    @Suppress("UNCHECKED_CAST")
    @SuppressLint("HandlerLeak")
    override fun onLooperPrepared() {
        requestHandler = object : Handler(){
            override fun handleMessage(msg: Message) {
                if (msg.what == MESSAGE_DOWNLOAD){
                    
                    // T는 제네릭. msg.obj가 T 타입이 될 수 없는 경우 Lint가 경고함 (@SuppressLint로 메시지 출력 억제)
                    val target = msg.obj as T
                    Log.i(TAG, "Got a request for URL : ${requestMap[target]}")
                    
                    // 내려받기 수행
                    handleRequest(target)
                }
            }
        }
    }

    override fun quit(): Boolean{
        hasQuit = true
        return super.quit()
    }

    fun queueThumbnail(target: T, url: String){
        Log.i(TAG, "Got a URL: $url")
        
        // 메시지를 가져와서 전달하기
        requestMap[target] = url
        requestHandler.obtainMessage(MESSAGE_DOWNLOAD, target).sendToTarget()
    }

    private fun handleRequest(target: T){
        val url = requestMap[target] ?: return
        val bitmap = flickrFetchr.fetchPhoto(url) ?: return
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun setup(){
        Log.i(TAG, "Starting background thread")
        start()
        looper
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun tearDown(){
        Log.i(TAG, "Destroying background thread")
        quit()
    }
}