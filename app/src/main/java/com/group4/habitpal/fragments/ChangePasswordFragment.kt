package com.group4.habitpal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.group4.habitpal.R
import com.group4.habitpal.activities.MainActivity
import com.group4.habitpal.custom_views.CustomAppButton
import com.parse.ParseUser

class ChangePasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_changepassword, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val mainActivity = requireActivity() as MainActivity

        val currentPassword = mainActivity.findViewById<EditText>(R.id.field_currentpassword).text.toString()
        val newPassword = mainActivity.findViewById<EditText>(R.id.field_newpassword).text.toString()
        val newPasswordConfirmation = mainActivity.findViewById<EditText>(R.id.field_newpasswordagain).text.toString()
        val btnSavePassword = mainActivity.findViewById<CustomAppButton>(R.id.changepassword_btn_changepassword)

        mainActivity.showBackButton(MyProfileFragment())

        /*btnSavePassword.setOnClickListener {
            if((currentPassword == ParseUser().getPassword()) && (newPassword == newPasswordConfirmation)) {
                ParseUser().setPassword(newPassword)
            }

        }*/


    }


}