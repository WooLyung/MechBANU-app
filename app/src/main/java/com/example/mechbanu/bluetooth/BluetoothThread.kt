package com.example.mechbanu.bluetooth

import android.bluetooth.BluetoothSocket
import android.content.Context
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import com.example.mechbanu.utils.reciever
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception

class BluetoothThread(val socket: BluetoothSocket, val context: Context) : Thread() {
    private val inStream: InputStream?
    private val outStream: OutputStream?

    val isConnected: Boolean
        get() = socket.isConnected

    override fun run() {
        val buffer = ByteArray(1024)
        var bytes: Int

        if (inStream == null || outStream == null)
            return

        while (true) {
            try {
                bytes = inStream.available()
                if (bytes != 0) {
                    inStream.read(buffer, 0, bytes)
                    reciever?.readPacket(buffer)
                }
            } catch (e: Exception) {
                Log.i("BANUBANU", e.message!!)
                break
            }
        }
    }

    fun write(bytes: ByteArray) {
        outStream?.write(bytes)
    }

    fun close() {
        try {
            socket.close()
        } catch (e: Exception) {
            Toast.makeText(context, "소켓 해제 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
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