package com.example.olga.sentenceskotlinmvp.main

import android.content.Context
import android.content.Intent

interface MainInterface {

    interface View {
        fun startSentenceActivity(intent: Intent)
        fun showInternetDialog()
        fun ifComplete(sentList: ArrayList<String>, lng: String)
    }

    interface Model {}

    interface Presenter {
        fun  makingDataToSentencesActivity(currentSent: String?, context: Context, lng: String)
        fun downloadSentences(lng: String)
    }
}