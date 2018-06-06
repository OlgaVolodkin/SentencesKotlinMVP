package com.example.olga.sentenceskotlinmvp.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.olga.sentenceskotlinmvp.R
import com.example.olga.sentenceskotlinmvp.R.string.internet
import com.example.olga.sentenceskotlinmvp.consts.Consts.Main.ENG
import com.example.olga.sentenceskotlinmvp.consts.Consts.Main.HEB
import com.example.olga.sentenceskotlinmvp.ui.NoInternetDialogFragment
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

    override fun onResume() {
        super.onResume()
        stopProgressBar()
    }

    private fun setInitial() {
        presenter = MainPresenter(this)
    }

    private fun setupListeners() {
        engBtn.setOnClickListener{
            startProgressBar()
            presenter?.downloadSentences(ENG)
        }
        hebBtn.setOnClickListener {
            startProgressBar()
            presenter?.downloadSentences(HEB)
        }
    }

    private fun stopProgressBar() { progressBar.visibility = GONE }
    private fun startProgressBar() { progressBar.visibility  = VISIBLE }

    override fun ifComplete(sentList: ArrayList<String>, lng: String) {
        if (sentList.size != 0) {
            val i = Random().nextInt(sentList.size)
            val currentSent = sentList[i]
            presenter?.makingDataToSentencesActivity(currentSent, this@MainActivity, lng)
        } else {
            showInternetDialog()
        }
    }

    override fun showInternetDialog() {
        stopProgressBar()
        supportFragmentManager.beginTransaction().add(NoInternetDialogFragment(),
                getString(internet)).commitAllowingStateLoss()
    }

    override fun startSentenceActivity(intent: Intent) {
        startActivity(intent)
    }
}
