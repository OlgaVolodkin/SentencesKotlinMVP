package com.example.olga.sentenceskotlinmvp.sentences

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.olga.sentenceskotlinmvp.R
import com.example.olga.sentenceskotlinmvp.R.string.sent_str
import kotlinx.android.synthetic.main.activity_sentences.*

class SentencesActivity : AppCompatActivity(), SentencesInterface.View {

    private var presenter: SentencesPresenter? = null
    private val TAG = "SentencesActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sentences)
        setInitial()
        checkData()
    }

    private fun checkData() {
        val intentSent = intent
        if (isDataAvailable(intentSent)) {
            presenter?.setSentence(intentSent, this)
        } else {
            Log.d(TAG, "Intent is null")
        }
    }

    private fun setInitial() {
        presenter = SentencesPresenter(this)
    }

    private fun isDataAvailable(intentData: Intent): Boolean {
        return intentData.hasExtra(getString(sent_str))
    }

    override fun setSentenceText(currentSentence: String) {
        Log.d(TAG, currentSentence)
        sentenceTextView.text = currentSentence
    }

    override fun setBackgroundPic(pic: Int) {
       parentLayout.setBackgroundResource(pic)
    }
}
