package com.example.mechbanu

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception
import java.util.*
import android.os.SystemClock

import android.bluetooth.BluetoothSocket
import android.os.Build
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


class MainActivity : AppCompatActivity() {
    var bluetoothManager: BluetoothManager? = null
    var bluetoothAdapter: BluetoothAdapter? = null
    private var threadConnectedBluetooth: ConnectedBluetoothThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val textView: TextView = findViewById(R.id.text)

        bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager?.adapter

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show()
        }
        else if (!bluetoothAdapter!!.isEnabled) {
            Toast.makeText(this, "Bluetooth is disabled.", Toast.LENGTH_LONG).show()
        }
        else {
            val listPairedDevices = bluetoothAdapter!!.bondedDevices
            var connectedDevice: BluetoothDevice? = null
            listPairedDevices.forEach {
                if (it.name == "MechBANU")
                    connectedDevice = it

                Log.i("BANU", it.name)
            }
            if (connectedDevice == null) {
                Toast.makeText(this, "MechBANU is not founded.", Toast.LENGTH_LONG).show()
            }
            else {
                try {
                    val bluetoothSocket = createBluetoothSocket(connectedDevice!!)
                    bluetoothSocket.connect()
                    threadConnectedBluetooth = ConnectedBluetoothThread(bluetoothSocket, this)
                    threadConnectedBluetooth?.start()
                }
                catch (e: Exception) {
                    Toast.makeText(this, "Error in Bluetooth.", Toast.LENGTH_LONG).show()
                    textView.text = e.message
                    e.printStackTrace()
                }
            }
        }

        button.setOnClickListener { view ->
            threadConnectedBluetooth?.write("이것은 테스트용 메세지!")
        }
    }

    private class ConnectedBluetoothThread(val mmSocket: BluetoothSocket, val context: Context) : Thread() {
        private val mmInStream: InputStream?
        private val mmOutStream: OutputStream?

        override fun run() {
            val buffer = ByteArray(1024)
            var bytes: Int

            if (mmInStream == null || mmOutStream == null)
                return

            while (true) {
                try {
                    bytes = mmInStream.available()
                    if (bytes != 0) {
                        SystemClock.sleep(100)
                        bytes = mmInStream.available()
                        bytes = mmInStream.read(buffer, 0, bytes)
                    }
                } catch (e: Exception) {
                    break
                }
            }
        }

        fun write(str: String) {
            val bytes = (str + "\n").toByteArray()
            try {
                mmOutStream?.write(bytes)
            } catch (e: Exception) {
                Toast.makeText(context, "데이터 전송 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
            }
        }

//        fun cancel() {
//            try {
//                mmSocket.close()
//            } catch (e: Exception) {
//                Toast.makeText(context, "소켓 해제 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
//            }
//        }

        init {
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null
            try {
                tmpIn = mmSocket.inputStream
                tmpOut = mmSocket.outputStream
            } catch (e: Exception) {
                Toast.makeText(context, "소켓 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
            }

            mmInStream = tmpIn
            mmOutStream = tmpOut
        }
    }

    private fun createBluetoothSocket(device: BluetoothDevice): BluetoothSocket {
        val uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
        if (Build.VERSION.SDK_INT >= 10) {
            return device.createInsecureRfcommSocketToServiceRecord(uuid)
        }
        return device.createRfcommSocketToServiceRecord(uuid)
    }
}