package com.group4.habitpal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.group4.habitpal.custom_views.CustomAppButton
import com.group4.habitpal.R
import com.group4.habitpal.activities.TitleActivity

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val titleActivity = requireActivity() as TitleActivity
        val btnSignUp = requireActivity().findViewById<CustomAppButton>(R.id.btn_signup_frag)

        btnSignUp.setAction {
            titleActivity.goToHomeScreen()
        }

    }


}