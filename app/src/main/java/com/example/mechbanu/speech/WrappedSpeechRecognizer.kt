package com.example.mechbanu.speech

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import com.example.mechbanu.bluetooth.BluetoothConnectService

class WrappedSpeechRecognizer(val context: Context) {
    var sttIntent: Intent? = null
    var recognizer: SpeechRecognizer? = null

    init {
        sttIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizer = SpeechRecognizer.createSpeechRecognizer(context)
        recognizer?.setRecognitionListener(object: RecognitionListener {
            override fun onReadyForSpeech(p0: Bundle?) = Unit

            override fun onBeginningOfSpeech() = Unit

            override fun onRmsChanged(p0: Float) = Unit

            override fun onBufferReceived(p0: ByteArray?) = Unit

            override fun onEndOfSpeech() = Unit

            override fun onError(p0: Int) {
                // button.setColorFilter(Color.WHITE)
            }

            override fun onResults(results: Bundle) {
                // button.setColorFilter(Color.WHITE)

                val matches: ArrayList<String> = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)!!
                var txt = ""

                matches.forEach { str ->
                    txt += str
                }

                BluetoothConnectService.instance?.bluetooth?.write((txt + "\n").toByteArray())
                Log.i("BANUBANU", txt)
            }

            override fun onPartialResults(p0: Bundle?) = Unit

            override fun onEvent(p0: Int, p1: Bundle?) = Unit
        })
    }

    fun startListening() {
        recognizer?.startListening(sttIntent)
    }
}