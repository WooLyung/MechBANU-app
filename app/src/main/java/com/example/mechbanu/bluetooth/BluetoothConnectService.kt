package com.example.mechbanu.bluetooth

import android.app.*
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.mechbanu.MainActivity
import com.example.mechbanu.R
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

        Thread {
            while (true) {
                if (bluetooth?.isConnected == false) {
                    bluetooth?.connect()
                }
                else {
                    Log.i("BANUBANU", "ping")
                    try {
                        bluetooth?.write("ping\n".toByteArray())
                    } catch (e: Exception) {
                        if (e.message == "Broken pipe") {
                            bluetooth?.connect()
                        }
                    }
                }

                Thread.sleep(10000)
            }
        }.start()

        return START_STICKY
    }

    override fun onDestroy() {
        bluetooth?.disconnect()
    }
}