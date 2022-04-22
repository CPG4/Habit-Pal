package com.group4.habitpal.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.group4.habitpal.custom_views.CustomAppButton
import com.group4.habitpal.R
import com.group4.habitpal.activities.TitleActivity
import com.group4.habitpal.models.User
import com.parse.ParseUser
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
        val btnSignUp = titleActivity.findViewById<CustomAppButton>(R.id.btn_signup_frag)

        btnSignUp.setOnClickListener {
            val username = titleActivity.findViewById<EditText>(R.id.signup_field_name).text.toString()
            val email = titleActivity.findViewById<EditText>(R.id.signup_field_email).text.toString()
            val password = titleActivity.findViewById<EditText>(R.id.signup_field_password).text.toString()
            val date_of_birth = Date(titleActivity.findViewById<EditText>(R.id.signup_field_dob).text.toString())

            signUpUser(username, email, password, date_of_birth)

        }



    }


    private fun signUpUser(username: String, email: String, password: String, date_of_birth: Date) {
        val titleActivity = requireActivity() as TitleActivity
        val user = User()
        user.setUsername(username)
        user.setEmail(email)
        user.setPassword(password)
        user.setDateOfBirth(date_of_birth)


           user.signUpInBackground { e ->
            if (e == null) {
                Toast.makeText(requireContext(), "signup succeeded", Toast.LENGTH_SHORT).show()
                titleActivity.goToHomeScreen()
            } else {
                Toast.makeText(requireContext(), "signup failed", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }

    }




}


