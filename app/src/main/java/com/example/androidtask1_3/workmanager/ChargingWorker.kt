package com.example.androidtask1_3.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class ChargingWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val notificationHelper = NotificationHelper(applicationContext)
        notificationHelper.showNotification()
        return Result.success()
    }
}
