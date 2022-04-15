package com.group4.habitpal.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.group4.habitpal.R
import com.group4.habitpal.activities.MainActivity
import com.group4.habitpal.custom_views.CustomAppButton

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val mainActivity = requireActivity() as MainActivity

        val btnMyProfile = requireActivity().findViewById<CustomAppButton>(R.id.btn_myprofile)
        val btnSettings = requireActivity().findViewById<CustomAppButton>(R.id.btn_settings)
        val btnLogOut = requireActivity().findViewById<CustomAppButton>(R.id.btn_logout)
        val btnTerms = requireActivity().findViewById<CustomAppButton>(R.id.btn_terms)
        val btnPrivacy = requireActivity().findViewById<CustomAppButton>(R.id.btn_privacy)

        btnMyProfile.setAction {
            mainActivity.replaceFragment(SettingsFragment())
        }

        btnSettings.setAction {
            mainActivity.replaceFragment(SettingsFragment())
        }

        btnTerms.setAction {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/CPG4/Habit-Pal")
                )
            )
        }

        btnPrivacy.setAction {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/CPG4/Habit-Pal")
                )
            )
        }

        btnLogOut.setAction {
            mainActivity.goToTitleScreen()
        }

    }


}