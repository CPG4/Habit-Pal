package com.group4.habitpal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.group4.habitpal.R
import com.group4.habitpal.activities.MainActivity
import com.group4.habitpal.custom_views.CustomAppButton

class MyProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_myprofile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val mainActivity = requireActivity() as MainActivity
        val btnEditProfile = mainActivity.findViewById<CustomAppButton>(R.id.btn_editprofile)
        val btnChangePassword = mainActivity.findViewById<CustomAppButton>(R.id.btn_changepassword)

        mainActivity.showBackButton(ProfileFragment())

        btnEditProfile.setAction {
            mainActivity.replaceFragment(EditProfileFragment())
        }

        btnChangePassword.setAction {
            mainActivity.replaceFragment(ChangePasswordFragment())
        }

    }


}