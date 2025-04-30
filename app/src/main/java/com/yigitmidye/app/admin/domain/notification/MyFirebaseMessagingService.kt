package com.yigitmidye.app.admin.domain.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import androidx.core.net.toUri
import com.yigitmidye.app.admin.MainActivity
import com.yigitmidye.app.admin.R

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val CHANNEL_ID = "default_channel"
    }

    private val soundUri by lazy {
        Uri.parse("android.resource://$packageName/raw/notification_sound")
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.notification?.let { notification ->
            val title = notification.title ?: "Başlık Yok"
            val body = notification.body ?: "Mesaj Yok"

            Log.d("FCM", "Bildirim alındı: $title - $body")

            showNotification(title, body)
        }
    }

    private fun showNotification(title: String, message: String) {
        // 1. İzin kontrolü (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.w("FCM", "Bildirim izni reddedildi!")
                return // İzin yoksa işlemi iptal et
            }
        }

        // 2. Bildirim oluşturma (izin varsa devam et)
        val notificationId = System.currentTimeMillis().toInt()

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.logo_svg_1)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSound(soundUri)
            .build()

        // 3. Bildirimi gönder (try-catch ile güvenli çağrı)
        try {
            NotificationManagerCompat.from(this).notify(notificationId, notification)
        } catch (e: SecurityException) {
            Log.e("FCM", "Bildirim gönderilemedi - İzin hatası: ${e.message}")
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val soundUri = "android.resource://$packageName/raw/notification_sound".toUri()

            val channel = NotificationChannel(
                CHANNEL_ID,
                "Default Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Sipariş bildirimleri"
                setSound(soundUri,
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                        .build()
                )
                enableVibration(true)
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            }

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "Yeni Token: $token")
    }
}