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

class Classifier_TodayDust : IClassifier {
    override val speechs = arrayListOf(
        "오늘뭔지",
        "오늘뭔지알려줘",
        "오늘뭔지어때",
        "바깥뭔지",
        "바깥뭔지알려줘",
        "바깥뭔지어때",
        "실외뭔지",
        "실외민저알려줘",
        "실외뭔지어때",
        "오늘미세뭔지",
        "오늘미세뭔지알려줘",
        "오늘미세뭔지어때",
        "바깥미세뭔지",
        "바깥미세뭔지알려줘",
        "바깥미세뭔지어때",
        "실외미세뭔지",
        "실외미세민저알려줘",
        "실외미세뭔지어때",
        "오늘먼지",
        "오늘먼지알려줘",
        "오늘먼지어때",
        "바깥먼지",
        "바깥먼지알려줘",
        "바깥먼지어때",
        "실외먼지",
        "실외민저알려줘",
        "실외먼지어때",
        "오늘미세먼지",
        "오늘미세먼지알려줘",
        "오늘미세먼지어때",
        "바깥미세먼지",
        "바깥미세먼지알려줘",
        "바깥미세먼지어때",
        "실외미세먼지",
        "실외미세민저알려줘",
        "실외미세먼지어때"
    )

    override fun process() {
        Thread {
            val url = URL("http://api.openweathermap.org/data/2.5/air_pollution?lon=128.61&lat=35.89&appid=${PrivateKey.WEATHER_KEY}")
            val conn = url.openConnection() as HttpURLConnection

            conn.connectTimeout = 10000
            conn.requestMethod = "GET"
            conn.doInput = true

            if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val lines = reader.readLines().joinToString { it }
                val json = Json.parseToJsonElement(lines) as JsonObject
                val dust = (json.get("list")?.jsonArray?.get(0)?.jsonObject?.get("components")?.jsonObject?.get("pm10")?.toString()?.toFloat() ?: 0.0F) +
                        (json.get("list")?.jsonArray?.get(0)?.jsonObject?.get("components")?.jsonObject?.get("pm2_5")?.toString()?.toFloat() ?: 0.0F)

                DisplayEditor.sendDustPacket(dust)

                reader.close()
            }

            conn.disconnect()
        }.start()
    }
}