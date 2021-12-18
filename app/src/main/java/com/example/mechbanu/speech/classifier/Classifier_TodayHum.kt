package com.example.mechbanu.speech.classifier

import android.util.Log
import com.example.mechbanu.BuildConfig
import com.example.mechbanu.neopixel.DisplayEditor
import com.example.mechbanu.packet.instance.DataRequestPacket
import com.example.mechbanu.speech.IClassifier
import com.example.mechbanu.utils.PrivateKey
import com.example.mechbanu.utils.sender
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Classifier_TodayHum : IClassifier {
    override val speechs = arrayListOf(
        "오늘습도",
        "오늘습도알려줘",
        "오늘습도어때",
        "바깥습도",
        "바깥습도알려줘",
        "바깥습도어때",
        "실외습도",
        "실외습도알려줘",
        "실외습도어때"
    )

    override fun process() {
        Thread {
            val url = URL("http://api.openweathermap.org/data/2.5/weather?q=Daegu&appid=${PrivateKey.WEATHER_KEY}")
            val conn = url.openConnection() as HttpURLConnection

            conn.connectTimeout = 10000
            conn.requestMethod = "GET"
            conn.doInput = true

            if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val lines = reader.readLines().joinToString { it }
                val json = Json.parseToJsonElement(lines) as JsonObject
                val hum = json.get("main")?.jsonObject?.get("humidity")?.toString()?.toFloat() ?: 0.0F

                DisplayEditor.sendHumPacket(hum)

                reader.close()
            }

            conn.disconnect()
        }.start()
    }
}