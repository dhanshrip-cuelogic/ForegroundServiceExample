package com.example.foregroundserviceexample


import com.example.foregroundserviceexample.App.Companion.CHANNEL_ID
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat

class ForegroundService : Service() {

    lateinit var myPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        myPlayer = MediaPlayer.create(this, R.raw.track)
        myPlayer.isLooping = false
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        myPlayer.start()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Forground Service")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        myPlayer.stop()
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}

//class ForegroundService : Service() {
//    var myPlayer: MediaPlayer? = null
//    override fun onCreate() {
//        super.onCreate()
//        myPlayer = MediaPlayer.create(this, R.raw.track)
//        myPlayer!!.isLooping = false // Set looping
//    }
//
//    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
//        myPlayer!!.start()
//        val notificationIntent = Intent(this, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(
//            this,
//            0, notificationIntent, 0
//        )
//
//        //Build a notification
//        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setContentTitle("Forground Service")
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setContentIntent(pendingIntent)
//            .build()
//        //A notifcation HAS to be passed for the foreground service to be started.
//        startForeground(1, notification)
//        return START_NOT_STICKY
//    }
//
//    override fun onDestroy() {
//        myPlayer!!.stop()
//    }
//
//    //Used for Bound service,At this point let's keep it null
//    @Nullable
//    override fun onBind(intent: Intent): IBinder? {
//        return null
//    }
//}