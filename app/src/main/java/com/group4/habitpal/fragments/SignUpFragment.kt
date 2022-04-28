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
import java.text.SimpleDateFormat
import java.util.*

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

//        btnSignUp.setAction {
//            titleActivity.goToHomeScreen()
//        }

        btnSignUp.setOnClickListener {
            val name = view.findViewById<EditText>(R.id.signup_field_name).text.toString()
            val email = view.findViewById<EditText>(R.id.signup_field_email).text.toString()
            val password = view.findViewById<EditText>(R.id.signup_field_password).text.toString()

//            val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
//            val dob = dateFormatter.parse(view.findViewById<EditText>(R.id.field_email).text.toString())
//            val dob = "dummyData"

            val dateOfBirth = Date(titleActivity.findViewById<EditText>(R.id.signup_field_dob).text.toString())

            signUpUser(name, email, password, dateOfBirth)
        }

    }

    private fun signUpUser(name: String, email: String, password: String, dateOfBirth: Date){
        // Create the ParseUser
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(email)
        user.setPassword(password)
        user.setEmail(email)
        user.put("date_of_birth", dateOfBirth)

        user.signUpInBackground { e ->
            if (e == null) {
                // user has successfully created a new account
                Toast.makeText(requireContext(), "Successfully signed up!", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Successfully signed up user!")
                val titleActivity = requireActivity() as TitleActivity
                titleActivity.goToHomeScreen()
            } else {
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
                e.printStackTrace()
                //TODO: Find out why this error toast keeps appearing
                Toast.makeText(requireContext(), "Error signing up!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object{
        const val TAG = "SignUpFragment"
    }
}