package com.example.olga.sentenceskotlinmvp.managers

import retrofit2.Response
import java.util.*

interface DownloadSentencesManagerInterface {

    interface ModelListener {

        fun downloadSentencesSuccess(response: Response<ArrayList<String>>, lng: String)
        fun downloadSentencesFailure()
    }

    fun downloadSentences(lng: String, listener: ModelListener)

}