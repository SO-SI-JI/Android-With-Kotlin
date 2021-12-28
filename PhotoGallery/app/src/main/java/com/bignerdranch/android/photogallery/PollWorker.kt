package com.bignerdranch.android.photogallery

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

private const val TAG = "PollWorker"

class PollWorker(val context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {
    
    // doWork() 함수는 백그라운드 스레드에서 호출 -> 오래 실행되는 (최대 10분) 작업을 할 수 있다.
    override fun doWork(): Result {
        Log.i(TAG, "Work request triggered")
        
        // 작업이 성공했음
        return Result.success()
    }
}