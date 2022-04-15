package com.group4.habitpal.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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

        findViewById<View>(R.id.title_screen).setOnClickListener { view ->
            view.clearFocus()
            hideKeyboard()
        }

        hideBackButton()

        findViewById<CustomAppButton>(R.id.btn_login).setAction {
            goToScreen(LoginFragment())
        }

        findViewById<CustomAppButton>(R.id.btn_signup).setAction {
            goToScreen(SignUpFragment())
        }

        findViewById<CustomAppButton>(R.id.title_btn_back).setAction {
            showTitle()
        }

    }

    /**
     *
     * Removes the current screen from view, shows the title
     * screen, and hides the back button.
     *
     * @author Sisiame B. Sakasamo
     *
     */
    private fun showTitle() {

        supportFragmentManager
            .beginTransaction()
            .remove(currentScreen)
            .commit()

        findViewById<ConstraintLayout>(R.id.title).visibility = View.VISIBLE

        hideBackButton()

    }

    /**
     *
     * Hides the title screen and makes the back button visible.
     *
     * @author Sisiame B. Sakasamo
     *
     */
    private fun hideTitle() {
        findViewById<ConstraintLayout>(R.id.title).visibility = View.GONE
        showBackButton()
    }

    /**
     *
     * Makes the back button visible.
     *
     * @author Sisiame B. Sakasamo
     *
     */
    private fun showBackButton() {
        findViewById<View>(R.id.title_btn_back).visibility = View.VISIBLE
        findViewById<View>(R.id.btn_back_bg).visibility = View.VISIBLE
    }

    /**
     *
     * Hides the back button.
     *
     * @author Sisiame B. Sakasamo
     *
     */
    private fun hideBackButton() {
        findViewById<View>(R.id.title_btn_back).visibility = View.GONE
        findViewById<View>(R.id.btn_back_bg).visibility = View.GONE
    }

    /**
     *
     * Hides the title screen and displays a new screen in its
     * place.
     *
     * @param fragment the screen being displayed
     *
     * @author Sisiame B. Sakasamo
     *
     */
    private fun goToScreen(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        currentScreen = fragment

        hideTitle()

    }

    /**
     *
     * Hides the on-screen keyboard.
     *
     * @author Sisiame B. Sakasamo
     *
     */
    private fun hideKeyboard() {

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(window.decorView.windowToken, 0)

    }

    /**
     *
     * Moves to the home screen.
     *
     * @author Sisiame B. Sakasamo
     *
     */
    fun goToHomeScreen() {
        startActivity(Intent(this@TitleActivity, MainActivity::class.java))
        finish()
    }

}