package com.example.mechbanu

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mechbanu.bluetooth.BluetoothConnectService
import com.example.mechbanu.speech.WrappedSpeechRecognizer
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var recognizer: WrappedSpeechRecognizer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recognizer = WrappedSpeechRecognizer(this)

        if (BluetoothConnectService.instance == null) {
            val intent = Intent(this, BluetoothConnectService::class.java)
            startService(intent)
        }

        val button: ImageButton = findViewById(R.id.microphone)
        button.setColorFilter(Color.WHITE)

        button.setOnClickListener { view ->
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
            }
            else {
                try {
                    button.setColorFilter(Color.CYAN)
                    recognizer?.startListening()
                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}