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
import com.example.mechbanu.bluetooth.Bluetooth
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


class MainActivity : AppCompatActivity() {
    var bluetooth: Bluetooth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val textView: TextView = findViewById(R.id.text)

        bluetooth = Bluetooth(this)

        button.setOnClickListener { view ->
            bluetooth?.write("이것은 테스트용 메세지!\n".toByteArray())
        }
    }
}