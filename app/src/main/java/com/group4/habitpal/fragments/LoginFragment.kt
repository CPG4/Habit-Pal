package com.group4.habitpal.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.group4.habitpal.R
import com.group4.habitpal.activities.MainActivity
import com.group4.habitpal.activities.TitleActivity
import com.group4.habitpal.custom_views.CustomAppButton
import com.group4.habitpal.models.User
import com.parse.ParseUser


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
        val btnLogin = titleActivity.findViewById<CustomAppButton>(R.id.loginfrag_btn_login)

        btnLogin.setOnClickListener {
            val email = titleActivity.findViewById<EditText>(R.id.field_email).text.toString()
            val password = titleActivity.findViewById<EditText>(R.id.field_password).text.toString()
            loginUser(email, password)
        }


    }

    private fun loginUser(email: String, password: String) {
        val titleActivity = requireActivity() as TitleActivity
        ParseUser.logInInBackground(email, password, ({ user, e ->
            if(user != null) {
                titleActivity.goToHomeScreen()
            } else {
                Toast.makeText(requireContext(), "error logging in", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }))
    }



}