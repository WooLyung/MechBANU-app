package com.example.mechbanu.bluetooth

import android.app.*
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.mechbanu.MainActivity
import com.example.mechbanu.R
import com.example.mechbanu.packet.PacketReciever
import com.example.mechbanu.packet.PacketSender
import com.example.mechbanu.packet.instance.PingPacket
import java.lang.Exception
import javax.net.ssl.SSLEngineResult

class BluetoothConnectService : Service() {
    companion object {
        var instance: BluetoothConnectService? = null
            private set
    }

    enum class ConnectStatus {
        NONE, CONNECT, FAIL, CANT, DISABLE, UNFOUND
    }

    private var bluetooth: Bluetooth? = null
    private var notificationBuilder: Notification.Builder? = null
    val sender = PacketSender(this)
    val reciever = PacketReciever(this)

    var status = ConnectStatus.NONE
        private set

    fun notify(status: ConnectStatus) {
        when (status) {
            ConnectStatus.CONNECT -> {
                updateNotification("메카 반우와 연결되어 있습니다.")
            }
            ConnectStatus.FAIL -> {
                updateNotification("메카 반우와 연결하는 데에 실패했습니다.")
            }
            ConnectStatus.CANT -> {
                updateNotification("블루투스를 사용할 수 없는 기기입니다.")
            }
            ConnectStatus.DISABLE -> {
                updateNotification("블루투스가 비활성화 상태입니다.")
            }
            ConnectStatus.UNFOUND -> {
                updateNotification("메카 반우를 찾을 수 없습니다.")
            }
        }
        this.status = status
    }

    private fun updateNotification(text: String) {
        notificationBuilder?.setContentText(text)
        startForeground(1, notificationBuilder!!.build())
    }

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

        val channel = NotificationChannel("banu_channel", "mechbanu", NotificationManager.IMPORTANCE_NONE)
        channel.enableVibration(false)
        channel.vibrationPattern = longArrayOf(0)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        notificationBuilder = Notification.Builder(applicationContext, "banu_channel")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("MechBANU Bluetooth")
            .setContentIntent(pendingIntent)
            .setContentText("메카 반우를 찾고 있습니다.")

        notificationManager.notify(1, notificationBuilder!!.build())
        startForeground(1, notificationBuilder!!.build())

        Thread {
            while (true) {
                if (bluetooth?.isConnected == false) {
                    bluetooth?.connect()
                }
                else {
                    try {
                        sender.sendPacket(PingPacket())
                        if (status != ConnectStatus.CONNECT)
                            notify(ConnectStatus.CONNECT)
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

    fun write(bytes: ByteArray) {
        bluetooth?.write(bytes)
    }
}