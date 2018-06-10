package com.example.olga.sentenceskotlinmvp.ui.main

import android.content.Context
import android.content.Intent

interface MainInterface {

    interface View {
        fun startSentenceActivity(intent: Intent)
        fun showInternetDialog()
        fun setCurrentSentence(currentSent: String, sentenceLanguage: String)
    }

    interface Model {}

    interface Presenter {
        fun makingDataToSentencesActivity(currentSentence: String?, context: Context, sentenceLanguage: String)
        fun downloadSentences(sentenceLanguage: String)
    }
}