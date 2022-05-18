package com.example.foregroundservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class Foreground : Service() {

    val CHANNEL_ID = "ForegroundChannel"//서비스가 사용할 상수
    override fun onBind(intent: Intent): IBinder {
        return Binder()
    }
    fun createNotificationChannel(){//알림채널 생성
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()//알림 생성
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")//알림 제목
            .setSmallIcon(R.mipmap.ic_launcher_round)//알림에 사용할 아이콘
            .build()
        startForeground(1, notification)//생성한 알림 실행
        return super.onStartCommand(intent, flags, startId)
    }
}