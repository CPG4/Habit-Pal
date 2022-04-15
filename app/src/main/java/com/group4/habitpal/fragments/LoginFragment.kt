package com.group4.habitpal.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group4.habitpal.R
import com.group4.habitpal.activities.TitleActivity
import com.group4.habitpal.custom_views.CustomAppButton


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val titleActivity = requireActivity() as TitleActivity
        val btnLogin = titleActivity.findViewById<CustomAppButton>(R.id.btn_login_frag)

        btnLogin.setAction {
            titleActivity.goToHomeScreen()
        }

    }
}