package com.example.olga.sentenceskotlinmvp.ui.main

import android.content.Context
import android.content.Intent
import com.example.olga.sentenceskotlinmvp.R
import com.example.olga.sentenceskotlinmvp.managers.DownloadSentencesManager
import com.example.olga.sentenceskotlinmvp.managers.DownloadSentencesManagerInterface
import com.example.olga.sentenceskotlinmvp.ui.sentences.SentencesActivity
import retrofit2.Response
import java.util.*

class MainPresenter (view: MainInterface.View): MainInterface.Presenter, DownloadSentencesManagerInterface.DownloadListener {

    private val TAG = "MainPresenter"
    private var view: MainInterface.View? = null
    private var downloadManager: DownloadSentencesManagerInterface? = null

    init {
        this.view = view
        this.downloadManager = DownloadSentencesManager()
    }


    override fun makingDataToSentencesActivity(currentSentence: String?, context: Context, sentenceLanguage: String) {
        val intent = Intent(context, SentencesActivity::class.java)
        intent.putExtra(context.getString(R.string.sent_str), currentSentence)
        intent.putExtra(context.getString(R.string.language), sentenceLanguage)
        view?.startSentenceActivity(intent)
    }


    override fun downloadSentences(sentenceLanguage: String) {
        downloadManager?.downloadSentences(sentenceLanguage, this)
    }


    override fun downloadSentencesSuccess(downloadResponse: Response<ArrayList<String>>, sentenceLanguage: String) {
        if (downloadResponse.body()?.size != 0) {
            val currentSent = getSentence(downloadResponse.body()!!)
            view?.setCurrentSentence(currentSent, sentenceLanguage)
        }
        else {
            view?.showInternetDialog()
        }
    }

    private fun getSentence(sentList: ArrayList<String>): String {
        val i = Random().nextInt(sentList.size)
        return sentList[i]
    }

    override fun downloadSentencesFailure() {
        view?.showInternetDialog()
    }
}