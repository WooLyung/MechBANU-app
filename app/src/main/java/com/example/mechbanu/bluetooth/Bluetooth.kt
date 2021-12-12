package com.example.mechbanu.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.os.Build
import android.widget.Toast
import java.lang.Exception
import java.util.*

class Bluetooth(val context: Context) {
    private var bluetoothManager: BluetoothManager? = null
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var thread: BluetoothThread? = null

    var isConnected = false
        private set

    init {
        bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager?.adapter
        connect()
    }

    fun write(bytes: ByteArray) {
        thread?.write(bytes)
    }

    fun connect() {
        if (bluetoothAdapter == null) {
            Toast.makeText(context, "블루투스를 사용할 수 없는 기기입니다.", Toast.LENGTH_LONG).show()
        }
        else if (!bluetoothAdapter!!.isEnabled) {
            Toast.makeText(context, "블루투스가 비활성화 상태입니다.", Toast.LENGTH_LONG).show()
        }
        else {
            val listPairedDevices = bluetoothAdapter!!.bondedDevices
            var connectedDevice: BluetoothDevice? = null
            listPairedDevices.forEach {
                if (it.name == "MechBANU")
                    connectedDevice = it
            }
            if (connectedDevice == null) {
                Toast.makeText(context, "메카 반우를 찾을 수 없습니다.", Toast.LENGTH_LONG).show()
            }
            else {
                try {
                    val bluetoothSocket = createBluetoothSocket(connectedDevice!!)
                    bluetoothSocket.connect()
                    thread = BluetoothThread(bluetoothSocket, context)
                    thread?.start()
                    isConnected = true
                }
                catch (e: Exception) {
                    Toast.makeText(context, "블루투스를 연결하는 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            }
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