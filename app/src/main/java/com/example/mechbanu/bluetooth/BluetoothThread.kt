package com.example.mechbanu.bluetooth

import android.bluetooth.BluetoothSocket
import android.content.Context
import android.os.SystemClock
import android.widget.Toast
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception

class BluetoothThread(val socket: BluetoothSocket, val context: Context) : Thread() {
    private val inStream: InputStream?
    private val outStream: OutputStream?

    override fun run() {
        val buffer = ByteArray(1024)
        var bytes: Int

        if (inStream == null || outStream == null)
            return

        while (true) {
            try {
                bytes = inStream.available()
                if (bytes != 0) {
                    SystemClock.sleep(100)
                    bytes = inStream.available()
                    bytes = inStream.read(buffer, 0, bytes)
                }
            } catch (e: Exception) {
                break
            }
        }
    }

    fun write(bytes: ByteArray) {
        try {
            outStream?.write(bytes)
        } catch (e: Exception) {
            Toast.makeText(context, "데이터 전송 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
        }
    }

    init {
        var tmpIn: InputStream? = null
        var tmpOut: OutputStream? = null
        try {
            tmpIn = socket.inputStream
            tmpOut = socket.outputStream
        } catch (e: Exception) {
            Toast.makeText(context, "소켓 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
        }

        inStream = tmpIn
        outStream = tmpOut
    }
}