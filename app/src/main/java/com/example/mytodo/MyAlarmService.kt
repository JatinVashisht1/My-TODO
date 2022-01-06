package com.example.mytodo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mytodo.core.Constants
import com.example.mytodo.presentation.MainActivity

@ExperimentalMaterialApi
class MyAlarmService() : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        createNotificationChannel(context = context!!)
        val builder = NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_alarm)
            .setContentText("Notification Deadline")
            .setContentTitle("Ding dong ding")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()


        NotificationManagerCompat.from(context).notify(123, builder)
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = Constants.CHANNEL_NAME
            val descriptionText = "Alarm goes off bitch"
            val importance = NotificationManagerCompat.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(Constants.CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH).apply {
                    description = descriptionText
                }
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
