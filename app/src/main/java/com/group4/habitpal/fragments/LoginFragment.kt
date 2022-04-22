package com.group4.habitpal.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.group4.habitpal.R
import com.group4.habitpal.activities.TitleActivity
import com.group4.habitpal.custom_views.CustomAppButton
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
            val username = view.findViewById<EditText>(R.id.field_email).text.toString()
            val password = view.findViewById<EditText>(R.id.field_password).text.toString()
            loginUser(username, password)
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                // Hooray!  The user is logged in.
                Toast.makeText(requireContext(), "Successfully logged in!", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Successfully logged in user!")
                val titleActivity = requireActivity() as TitleActivity
                titleActivity.goToHomeScreen()

            } else {
                // login failed.  Look at the ParseException to see what happened.
                e.printStackTrace()
                Toast.makeText(requireContext(), "Error logging in", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    companion object{
        const val TAG = "LoginFragment"
    }
}