package com.example.mechbanu

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import com.example.mechbanu.bluetooth.Bluetooth

class MainActivity : AppCompatActivity() {
    var bluetooth: Bluetooth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: ImageButton = findViewById(R.id.microphone)
        button?.setColorFilter(Color.WHITE)

        bluetooth = Bluetooth(this)

        button.setOnClickListener { view ->
            bluetooth?.write("이것은 테스트용 메세지!\n".toByteArray())
        }
    }
}