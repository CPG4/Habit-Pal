package com.group4.habitpal.activities

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnRepeat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.group4.habitpal.R
import com.group4.habitpal.custom_views.CustomAppButton
import com.group4.habitpal.fragments.*


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // removes focus from all EditText fields when the screen is
        // touched outside of the keyboard and EditText fields
        findViewById<View>(R.id.main_screen).setOnClickListener { view ->
            view.clearFocus()
            hideKeyboard()
        }

        animateHeaderFooter()
        hideBackButton()

        bottomNav = findViewById(R.id.bottom_navigation)

        bottomNav.setOnItemSelectedListener { item ->

            when(item.itemId) {

                R.id.action_addhabit -> {
                    replaceFragment(AddHabitFragment())
                }

                R.id.action_myhabits -> {
                    replaceFragment(MyHabitsFragment())
                }

                R.id.action_profile -> {
                    replaceFragment(ProfileFragment())
                }

            }

            true

        }

        bottomNav.selectedItemId = R.id.action_myhabits

    }

    /**
     *
     * Plays a slide in animation for the title and navigation bar.
     *
     * @author Sisiame B. Sakasamo
     *
     */
    private fun animateHeaderFooter() {

        val animationDuration: Long = 1000

        findViewById<View>(R.id.title).translationY = -600f
        findViewById<View>(R.id.header_bg).translationY = -600f
        findViewById<View>(R.id.bottom_navigation_container).translationY = 600f

        ObjectAnimator.ofFloat(findViewById(R.id.title), "translationY", 0f).apply {
            duration = animationDuration
            start()
        }

        ObjectAnimator.ofFloat(findViewById(R.id.header_bg), "translationY", 0f).apply {
            duration = animationDuration
            start()
        }

        ObjectAnimator.ofFloat(findViewById(R.id.bottom_navigation_container), "translationY", 0f).apply {
            duration = animationDuration
            start()
        }

    }

    private fun setInteractionDisabled(disabled : Boolean) {

        if (disabled) {

            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )

        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }

    }

    fun showBackButton(fragment: Fragment) {

        findViewById<View>(R.id.main_btn_back).visibility = View.VISIBLE
        findViewById<View>(R.id.btn_back_bg).visibility = View.VISIBLE

        findViewById<CustomAppButton>(R.id.main_btn_back).setAction {
            replaceFragment(fragment)
        }

    }

    private fun hideBackButton() {
        findViewById<View>(R.id.main_btn_back).visibility = View.GONE
        findViewById<View>(R.id.btn_back_bg).visibility = View.GONE
    }

    private fun hideKeyboard() {

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(window.decorView.windowToken, 0)

    }

    /**
     *
     * Replaces the fragment currently inflating the fragment container.
     *
     * Effectively switches the current screen, and animates a screen
     * transition each time this operation is done.
     *
     * @param fragment the fragment replacing the current fragment
     *
     * @author Sisiame B. Sakasamo
     *
     */
    fun replaceFragment(fragment: Fragment) {

        val animationDuration: Long = 750

        // animates the fragment moving down then moving back up
        ObjectAnimator.ofFloat(findViewById(R.id.fragment_container), "translationY", 50f).apply {
            duration = animationDuration
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            start()
        }

        // animates the fragment fading out then fading back in
        ObjectAnimator.ofFloat(findViewById(R.id.fragment_container), "alpha", 0f).apply {
            duration = animationDuration
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE

            // disables user input once animation starts
            setInteractionDisabled(true)

            // switches fragment once the animation reverses
            doOnRepeat {

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()

                hideBackButton()

            }

            // re-enables user input once animation ends
            doOnEnd {
                setInteractionDisabled(false)
            }

            start()

        }

    }

    fun goToTitleScreen() {
        startActivity(Intent(this@MainActivity, TitleActivity::class.java))
        finish()
    }

    companion object {
        const val TAG = "MainActivity"
    }


}