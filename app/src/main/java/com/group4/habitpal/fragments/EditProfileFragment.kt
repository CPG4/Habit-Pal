package com.group4.habitpal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.group4.habitpal.R
import com.group4.habitpal.activities.MainActivity
import com.group4.habitpal.custom_views.CustomAppButton
import com.group4.habitpal.custom_views.CustomEditTextContainer
import com.parse.ParseUser
import java.util.*

class EditProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_editprofile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val mainActivity = requireActivity() as MainActivity

        mainActivity.findViewById<EditText>(R.id.editprofile_field_name).hint = ParseUser.getCurrentUser().getUsername()
        mainActivity.findViewById<EditText>(R.id.editprofile_field_email).hint = ParseUser.getCurrentUser().getEmail()
        mainActivity.findViewById<EditText>(R.id.editprofile_field_dob).hint = ParseUser.getCurrentUser().get("date_of_birth").toString()

        val username = mainActivity.findViewById<EditText>(R.id.editprofile_field_name).text.toString()
        val email = mainActivity.findViewById<EditText>(R.id.editprofile_field_email).text.toString()
        val dob = mainActivity.findViewById<EditText>(R.id.editprofile_field_dob).text.toString()
        val btnSaveInformation = mainActivity.findViewById<CustomAppButton>(R.id.btn_applychanges)


        mainActivity.showBackButton(MyProfileFragment())

        btnSaveInformation.setAction {
            ParseUser.getCurrentUser().setUsername(username)
            ParseUser.getCurrentUser().setEmail(email)
            ParseUser.getCurrentUser().put("date_of_birth", dob)
            mainActivity.replaceFragment(MyProfileFragment())
        }


    }


}