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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mechbanu.bluetooth.Bluetooth
import com.example.mechbanu.bluetooth.BluetoothConnectService
import com.example.mechbanu.speech.WrappedSpeechRecognizer
import com.example.mechbanu.speech.classifier.Classifier_TodayHum
import com.example.mechbanu.speech.classifier.Classifier_TodayTemp
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
                Classifier_TodayHum().process()

                return@setOnClickListener

                if (BluetoothConnectService.instance == null) {
                    Toast.makeText(this, "메카 반우가 준비되지 않았어요.", Toast.LENGTH_SHORT).show()
                }
                else {
                    when (BluetoothConnectService.instance!!.status) {
                        BluetoothConnectService.ConnectStatus.NONE -> {
                            Toast.makeText(this, "메카 반우와 연결중이에요.", Toast.LENGTH_SHORT).show()
                        }
                        BluetoothConnectService.ConnectStatus.FAIL -> {
                            Toast.makeText(this, "메카 반우와 연결에 실패했어요.", Toast.LENGTH_SHORT).show()
                        }
                        BluetoothConnectService.ConnectStatus.UNFOUND -> {
                            Toast.makeText(this, "메카 반우를 찾을 수 없어요.", Toast.LENGTH_SHORT).show()
                        }
                        BluetoothConnectService.ConnectStatus.DISABLE -> {
                            Toast.makeText(this, "블루투스가 비활성화 상태에요.", Toast.LENGTH_SHORT).show()
                        }
                        BluetoothConnectService.ConnectStatus.CANT -> {
                            Toast.makeText(this, "블루투스를 사용할 수 없는 기기에요.", Toast.LENGTH_SHORT).show()
                        }
                        BluetoothConnectService.ConnectStatus.CONNECT -> {
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