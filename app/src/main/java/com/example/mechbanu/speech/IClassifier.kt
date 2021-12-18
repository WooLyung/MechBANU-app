package com.example.mechbanu.speech

interface IClassifier {
    val speechs: List<String>

    fun classify(text: String) : Boolean {
        return text in speechs
    }

    fun process()
}