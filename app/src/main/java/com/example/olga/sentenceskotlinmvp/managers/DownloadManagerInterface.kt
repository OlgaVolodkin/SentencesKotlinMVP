package com.example.olga.sentenceskotlinmvp.managers

import retrofit2.Response
import java.util.*

interface DownloadSentencesManagerInterface {

    interface DownloadListener {
        fun downloadSentencesSuccess(downloadResponse: Response<ArrayList<String>>, sentenceLanguage: String)
        fun downloadSentencesFailure()
    }

    fun downloadSentences(lng: String, listener: DownloadListener)
}