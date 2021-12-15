package com.example.mechbanu.speech.classificater

import android.util.Log
import com.example.mechbanu.speech.IClassificater

class Classificater_NowTemp : IClassificater {
    override val speechs = arrayListOf(
        "지금 온도",
        "지금 온도 알려줘",
        "지금 온도 어때"
    )

    override fun process() {
        Log.i("BANUBANU", "방 온도")
    }
}