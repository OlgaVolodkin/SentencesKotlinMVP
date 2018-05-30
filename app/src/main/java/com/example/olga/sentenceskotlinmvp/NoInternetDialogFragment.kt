package com.example.olga.sentenceskotlinmvp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.example.olga.sentenceskotlinmvp.R.string.dialog_internet_text
import com.example.olga.sentenceskotlinmvp.R.string.ok

class NoInternetDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Use the Builder class for convenient dialog construction
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setMessage(dialog_internet_text).
                //setPositiveButton(ok, DialogInterface.OnClickListener { dialog, whichButton ->
                setPositiveButton(ok, { _, _ -> System.exit(1) })

        return builder.create()
    }
}
