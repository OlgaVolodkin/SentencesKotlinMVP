package com.example.olga.sentenceskotlinmvp.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.olga.sentenceskotlinmvp.Consts.Main.ENG
import com.example.olga.sentenceskotlinmvp.Consts.Main.HEB
import com.example.olga.sentenceskotlinmvp.NoInternetDialogFragment
import com.example.olga.sentenceskotlinmvp.R
import com.example.olga.sentenceskotlinmvp.R.string.internet
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), MainInterface.View {

    private var presenter: MainPresenter? = null
    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListeners()
        setInitial()
    }

    private fun setInitial() {
        presenter = MainPresenter(this)
    }

    private fun setupListeners() {
        engBtn.setOnClickListener{
            presenter?.downloadSentences(ENG)
        }

        hebBtn.setOnClickListener {
            presenter?.downloadSentences(HEB)
        }
    }

    override fun ifComplete(sentList: ArrayList<String>?, lngFlag: Int) {
        if (sentList?.size != 0) {
            val i = Random().nextInt(sentList!!.size)
            val currentSent = sentList[i]
            presenter?.makingDataToSentencesActivity(currentSent, this@MainActivity, lngFlag)
        }
        else {  //if list is empty (no sents or not internet) - alert dialog (pop up window)
            showInternetDialog()
        }
    }

    override fun showInternetDialog() {
        supportFragmentManager.beginTransaction().add(NoInternetDialogFragment(), getString(internet)).commitAllowingStateLoss()
    }

    override fun startSentenceActivity(intent: Intent) {
        startActivity(intent)
    }

}
