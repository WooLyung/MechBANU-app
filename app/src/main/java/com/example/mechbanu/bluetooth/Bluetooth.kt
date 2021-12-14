package com.example.mechbanu.bluetooth

import android.bluetooth.*
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import java.lang.Exception
import java.util.*

class Bluetooth(val context: Context) {
    private var bluetoothManager: BluetoothManager? = null
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var thread: BluetoothThread? = null

    val isConnected: Boolean
        get() = thread?.isConnected ?: false

    init {
        bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager?.adapter
        connect()
    }

    fun write(bytes: ByteArray) {
        thread?.write(bytes)
    }

    fun disconnect() {
        try {
            thread?.socket?.close()
            thread?.close()
        }
        catch (e: Exception)
        {
        }
    }

    fun connect() {
        if (bluetoothAdapter == null) {
            BluetoothConnectService.instance?.notify(BluetoothConnectService.ConnectStatus.CANT)
        }
        else if (!bluetoothAdapter!!.isEnabled) {
            BluetoothConnectService.instance?.notify(BluetoothConnectService.ConnectStatus.DISABLE)
        }
        else {
            val listPairedDevices = bluetoothAdapter!!.bondedDevices
            var connectedDevice: BluetoothDevice? = null
            listPairedDevices.forEach {
                if (it.name == "MechBANU")
                    connectedDevice = it
            }
            if (connectedDevice == null) {
                BluetoothConnectService.instance?.notify(BluetoothConnectService.ConnectStatus.UNFOUND)
            }
            else {
                try {
                    val bluetoothSocket = createBluetoothSocket(connectedDevice!!)
                    bluetoothSocket.connect()
                    thread = BluetoothThread(bluetoothSocket, context)
                    thread?.start()
                    BluetoothConnectService.instance?.notify(BluetoothConnectService.ConnectStatus.CONNECT)
                }
                catch (e: Exception) {
                    BluetoothConnectService.instance?.notify(BluetoothConnectService.ConnectStatus.FAIL)
                }
            }
        }
    }

    private fun createBluetoothSocket(device: BluetoothDevice): BluetoothSocket {
        return device.createInsecureRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"))
    }
}