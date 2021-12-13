package com.example.mechbanu

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var sttIntent: Intent? = null
    var recognizer: SpeechRecognizer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BluetoothConnectService.instance == null) {
            val intent = Intent(this, BluetoothConnectService::class.java)
            startService(intent)
        }
        else {
            val intent = Intent(this, BluetoothConnectService::class.java)
            startService(intent)
        }

        val button: ImageButton = findViewById(R.id.microphone)
        button.setColorFilter(Color.WHITE)

        sttIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizer = SpeechRecognizer.createSpeechRecognizer(this)
        recognizer?.setRecognitionListener(object:RecognitionListener {
            override fun onReadyForSpeech(p0: Bundle?) = Unit

            override fun onBeginningOfSpeech() = Unit

            override fun onRmsChanged(p0: Float) = Unit

            override fun onBufferReceived(p0: ByteArray?) = Unit

            override fun onEndOfSpeech() = Unit

            override fun onError(p0: Int) {
                button.setColorFilter(Color.WHITE)
            }

            override fun onResults(results: Bundle) {
                button.setColorFilter(Color.WHITE)

                val matches: ArrayList<String> = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)!!
                var txt = ""

                matches.forEach { str ->
                    txt += str
                }

                BluetoothConnectService.instance?.bluetooth?.write((txt + "\n").toByteArray())
            }

            override fun onPartialResults(p0: Bundle?) = Unit

            override fun onEvent(p0: Int, p1: Bundle?) = Unit
        })

        button.setOnClickListener { view ->
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
            }
            else {
                try {
                    button.setColorFilter(Color.CYAN)
                    recognizer?.startListening(sttIntent)
                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}