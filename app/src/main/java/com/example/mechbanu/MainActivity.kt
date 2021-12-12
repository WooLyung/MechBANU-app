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
import com.example.mechbanu.bluetooth.Bluetooth
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var bluetooth: Bluetooth? = null
    var sttIntent: Intent? = null
    var recognizer: SpeechRecognizer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: ImageButton = findViewById(R.id.microphone)
        button.setColorFilter(Color.WHITE)

        bluetooth = Bluetooth(this)
        sttIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizer = SpeechRecognizer.createSpeechRecognizer(this)
        recognizer?.setRecognitionListener(object:RecognitionListener {
            override fun onReadyForSpeech(p0: Bundle?) {
                Log.i("BANU", "onReadyForSpeech")
            }

            override fun onBeginningOfSpeech() {
                Log.i("BANU", "onBeginningOfSpeech")
            }

            override fun onRmsChanged(p0: Float) {
                Log.i("BANU", "onRmsChanged")
            }

            override fun onBufferReceived(p0: ByteArray?) {
                Log.i("BANU", "onBufferReceived")
            }

            override fun onEndOfSpeech() {
                Log.i("BANU", "onEndOfSpeech")
            }

            override fun onError(p0: Int) {
                Log.i("BANU", "onError")
            }

            override fun onResults(results: Bundle) {
                val matches: ArrayList<String> = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)!!
                var txt = ""

                matches.forEach { str ->
                    txt += str
                }

                Log.i("BANU", txt)
            }

            override fun onPartialResults(p0: Bundle?) {
                Log.i("BANU", "onPartialResults")
            }

            override fun onEvent(p0: Int, p1: Bundle?) {
                Log.i("BANU", "onEvent")
            }

        })

        button.setOnClickListener { view ->
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
            }
            else {
                try {
                    recognizer?.startListening(sttIntent)
                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}