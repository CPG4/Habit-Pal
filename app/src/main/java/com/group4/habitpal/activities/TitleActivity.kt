package com.group4.habitpal.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.group4.habitpal.R
import com.group4.habitpal.custom_views.CustomAppButton
import com.group4.habitpal.fragments.LoginFragment
import com.group4.habitpal.fragments.SignUpFragment


class TitleActivity : AppCompatActivity() {

    private lateinit var currentScreen: Fragment

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)

        hideBackButton()

        findViewById<CustomAppButton>(R.id.btn_login).setAction {
            goToScreen(LoginFragment())
        }

        findViewById<CustomAppButton>(R.id.btn_signup).setAction {
            goToScreen(SignUpFragment())
        }

        findViewById<CustomAppButton>(R.id.btn_back).setAction {
            showTitle()
        }

    }

    private fun showTitle() {

        supportFragmentManager
            .beginTransaction()
            .remove(currentScreen)
            .commit()

        findViewById<ConstraintLayout>(R.id.title).visibility = View.VISIBLE

        hideBackButton()

    }

    private fun hideTitle() {
        findViewById<ConstraintLayout>(R.id.title).visibility = View.GONE
        showBackButton()
    }

    private fun showBackButton() {
        findViewById<View>(R.id.btn_back).visibility = View.VISIBLE
        findViewById<View>(R.id.btn_back_bg).visibility = View.VISIBLE
    }

    private fun hideBackButton() {
        findViewById<View>(R.id.btn_back).visibility = View.GONE
        findViewById<View>(R.id.btn_back_bg).visibility = View.GONE
    }

    private fun goToScreen(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        currentScreen = fragment

        hideTitle()

    }

    fun goToHomeScreen() {
        startActivity(Intent(this@TitleActivity, MainActivity::class.java))
        finish()
    }

}