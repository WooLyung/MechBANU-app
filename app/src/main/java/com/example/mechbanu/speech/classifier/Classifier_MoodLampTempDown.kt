package com.example.mechbanu.speech.classifier

import com.example.mechbanu.neopixel.MoodLampEditor
import com.example.mechbanu.speech.IClassifier

class Classifier_MoodLampTempDown : IClassifier {
    override val speechs = arrayListOf(
        "색온도내려",
        "색온도내려줘",
        "무드등색온도내려줘",
        "무드등색온도내려"
    )

    override fun process() {
        MoodLampEditor.tempDown()
    }
}