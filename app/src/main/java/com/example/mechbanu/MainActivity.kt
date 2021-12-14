package com.example.mechbanu

import android.Manifest
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.animation.AnimationUtils
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

        if (BluetoothConnectService.instance == null) {
            val intent = Intent(this, BluetoothConnectService::class.java)
            startService(intent)
        }

        val button: ImageButton = findViewById(R.id.microphone)
        button.setColorFilter(Color.WHITE)

        recognizer = WrappedSpeechRecognizer(this)
        recognizer?.setOnSpeechEndListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.microphone_off)
            button.startAnimation(animation)
            changeColor(0xFFFFFFB3.toInt(), 0xFFFFFFFF.toInt(), button)
        }

        button.setOnClickListener { view ->
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
            }
            else {
                try {
                    val animation = AnimationUtils.loadAnimation(this, R.anim.microphone_on)
                    button.startAnimation(animation)
                    changeColor(0xFFFFFFFF.toInt(), 0xFFFFFFB3.toInt(), button)

                    recognizer?.startListening()
                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun changeColor(from: Int, to: Int, button: ImageButton) {
        val valueAnimator = ValueAnimator.ofObject(ArgbEvaluator(), from, to)
        valueAnimator.setDuration(200)
        valueAnimator.addUpdateListener { animator ->
            button.setColorFilter(animator.getAnimatedValue() as Int)
        }
        valueAnimator.start()
    }
}