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
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Classifier_TodayWeather : IClassifier {
    override val speechs = arrayListOf(
        "오늘날씨",
        "오늘날씨알려줘",
        "오늘날씨어때",
        "바깥날씨",
        "바깥날씨알려줘",
        "바깥날씨어때",
        "실외날씨",
        "실외날씨알려줘",
        "실외날씨어때",
        "날씨",
        "날씨알려줘",
        "날씨어때"
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
                val weather = json.get("weather")?.jsonArray?.get(0)?.jsonObject?.get("main")?.toString()?.let {
                    it.substring(1, it.length - 1)
                }

                DisplayEditor.sendWeatherPacket(weather)

                reader.close()
            }

            conn.disconnect()
        }.start()
    }
}