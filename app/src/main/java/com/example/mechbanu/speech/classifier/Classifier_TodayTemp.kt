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

class Classifier_TodayTemp : IClassifier {
    override val speechs = arrayListOf(
        "오늘온도",
        "오늘온도알려줘",
        "오늘온도어때",
        "바깥온도",
        "바깥온도알려줘",
        "바깥온도어때",
        "실외온도",
        "실외온도알려줘",
        "실외온도어때",
        "오늘기온",
        "오늘기온알려줘",
        "오늘기온어때",
        "바깥기온",
        "바깥기온알려줘",
        "바깥기온어때",
        "실외기온",
        "실외기온알려줘",
        "실외기온어때"
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
                val temp = json.get("main")?.jsonObject?.get("temp")?.toString()?.toFloat()?.let {
                    it - 273.15F
                } ?: 0.0F

                DisplayEditor.sendTempPacket(temp)

                reader.close()
            }

            conn.disconnect()
        }.start()
    }
}