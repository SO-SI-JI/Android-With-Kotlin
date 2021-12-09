package com.bignerdranch.android.photogallery

import android.os.Handler
import android.os.HandlerThread
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