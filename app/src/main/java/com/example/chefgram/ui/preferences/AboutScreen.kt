package com.example.chefgram.ui.preferences

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chefgram.R
import com.google.android.material.textview.MaterialTextView

class AboutScreen : Fragment(R.layout.about_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = getString(R.string.about_us, getString(R.string.date_update))
        view.findViewById<MaterialTextView>(R.id.privacy_policy_text).text = text
    }
}
