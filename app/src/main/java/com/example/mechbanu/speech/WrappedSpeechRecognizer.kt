package com.example.mechbanu.speech

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import com.example.mechbanu.bluetooth.BluetoothConnectService
import com.example.mechbanu.packet.instance.DisplayUpdatePacket
import com.example.mechbanu.utils.sender
import java.lang.Exception

class WrappedSpeechRecognizer(context: Context, var listener: (() -> Unit)? = null) {
    var sttIntent: Intent? = null
    var recognizer: SpeechRecognizer? = null

    fun setOnSpeechEndListener(listener: () -> Unit) {
        this.listener = listener
    }

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
                listener?.let { it() }

                Toast.makeText(context, "잘 알아듣지 못했어요.", Toast.LENGTH_LONG).show()
            }

            override fun onResults(results: Bundle) {
                listener?.let { it() }

                val matches: ArrayList<String> = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)!!
                var txt = ""
                matches.forEach { str ->
                    txt += str
                }

                try {
                    val packet = DisplayUpdatePacket()
                    packet.draw( "" +
                            "000000000000000000000000" +
                            "000000000000000001000000" +
                            "000010000000000010000000" +
                            "000001000000000100000000" +
                            "000000100000001000000100" +
                            "000000010000000000000100" +
                            "000000001000000000000100" +
                            "000000000000000000000000"
                    )

                    packet.setColor(0, 255, 255)
                    Log.i("BANUBANU", txt)

                    sender?.sendPacket(packet)
                }
                catch (e: Exception) {
                    Toast.makeText(context, "블루투스 통신 중에 문제가 발생했어요.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onPartialResults(p0: Bundle?) = Unit

            override fun onEvent(p0: Int, p1: Bundle?) = Unit
        })
    }

    fun startListening() {
        recognizer?.startListening(sttIntent)
    }
}