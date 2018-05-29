package com.example.olga.sentenceskotlinmvp.main

import android.content.Context
import android.content.Intent

interface MainInterface {

    interface View {
        fun startSentenceActivity(intent: Intent)
        fun showInternetDialog()
        fun ifComplete(sentList: ArrayList<String>?, lngFlag: Int)
    }

    interface Model {}

    interface Presenter {
        fun  makingDataToSentencesActivity(currentSent: String?, context: Context, flag: Int)
        fun downloadSentences(lngFlag: Int)
    }

}