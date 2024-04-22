package com.apptesting.test.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.apptesting.test.utils.SessionManager


open class BaseFragment : Fragment() {
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(requireActivity())
    }


}