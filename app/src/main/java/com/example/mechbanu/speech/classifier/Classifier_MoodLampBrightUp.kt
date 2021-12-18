package com.example.mechbanu.speech.classifier

import com.example.mechbanu.neopixel.MoodLampEditor
import com.example.mechbanu.speech.IClassifier

class Classifier_MoodLampBrightUp : IClassifier {
    override val speechs = arrayListOf(
        "밝기올려",
        "밝기올려줘",
        "무드등밝기올려줘",
        "무드등밝기올려"
    )

    override fun process() {
        MoodLampEditor.brightUp()
    }
}