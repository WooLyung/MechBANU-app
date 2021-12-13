package com.example.mechbanu

import android.app.*
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.mechbanu.bluetooth.Bluetooth
import java.lang.Exception

class BluetoothConnectService : Service() {
    companion object {
        var instance: BluetoothConnectService? = null
            private set
    }

    var bluetooth: Bluetooth? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (instance != null) {
            bluetooth?.connect()
            return START_NOT_STICKY
        }

        bluetooth = Bluetooth(applicationContext)
        instance = this

        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_CANCEL_CURRENT)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel("banu_channel", "mechbanu", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            val notification = Notification.Builder(applicationContext, "banu_channel")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("MechBANU")
                .setContentIntent(pendingIntent)
                .setContentText("")

            notificationManager.notify(1, notification.build())
            startForeground(1, notification.build())
        }

        Thread {
            while (true) {
                try {
                    // Log.i("BANUBANU", "${bluetooth?.isConnected}")
                    Thread.sleep(5000)
                } catch (e: Exception) {

                }
            }
        }.start()

        return START_STICKY
    }

    override fun onDestroy() {
        bluetooth?.disconnect()
    }
}