package com.bignerdranch.android.photogallery

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

private const val TAG = "PollWorker"

class PollWorker(val context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {
    
    // doWork() 함수는 백그라운드 스레드에서 호출 -> 오래 실행되는 (최대 10분) 작업을 할 수 있다.
    override fun doWork(): Result {
        val query = QueryPreferences.getStoredQuery(context)
        val lastResultId = QueryPreferences.getLastResultId(context)
        val items: List<GalleryItem> = if (query.isEmpty()){
            FlickrFetchr().fetchPhotosRequest().execute().body()?.photos?.galleryItems
        } else {
            FlickrFetchr().searchPhotosRequest(query).execute().body()?.photos?.galleryItems
        } ?: emptyList()

        if(items.isEmpty()){
            return Result.success()
        }

        val resultId = items.first().id
        if (resultId == lastResultId){
            Log.i(TAG, "Got an old result: $resultId")
        } else {
            Log.i(TAG, "Got a new result: $resultId")
            QueryPreferences.setLastResultId(context, resultId)
        }

        val intent = PhotoGalleryActivity.newIntent(context)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val resources = context.resources
        val notification = NotificationCompat
            .Builder(context, NOTIFICATION_CHANNEL_ID)
            .setTicker(resources.getString(R.string.new_pictures_title))
            .setSmallIcon(android.R.drawable.ic_menu_report_image)
            .setContentTitle(resources.getString(R.string.new_pictures_title))
            .setContentText(resources.getString(R.string.new_pictures_text))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        //val notificationManager = NotificationManagerCompat.from(context)
        //notificationManager.notify(0, notification)
        
        // 새로운 검색 결과를 게시할 준비가 되었음을 관심있는 컴포넌트에 알리는 브로드캐스트 인텐트를 전송
        //context.sendBroadcast(Intent(ACTION_SHOW_NOTIFICATION), PERM_PRIVATE)

        showBackgroudNotification(0, notification)

        // 작업이 성공했음
        return Result.success()
    }

    private fun showBackgroundNotification(requestCode: Int, notification: Notification){
        val intent = Intent(ACTION_SHOW_NOTIFICATION).apply {
            putExtra(REQUEST_CODE, requestCode)
            putExtra(NOTIFICATION, notification)
        }
        context.sendOrderedBroadcast(intent, PERM_PRIVATE)
    }

    companion object {
        const val ACTION_SHOW_NOTIFICATION = "com.bignerdranch.android.photogallery.SHOW_NOTIFICATION"
        const val PERM_PRIVATE = "com.bignerdranch.android.photogallery.PRIVATE"
        const val REQUEST_CODE = "REQUEST_CODE"
        const val NOTIFICATION = "NOTIFICATION"
    }
}