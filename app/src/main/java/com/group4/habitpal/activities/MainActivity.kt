package com.group4.habitpal.activities

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
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

    private fun animateHeaderFooter() {

        findViewById<View>(R.id.title).translationY = -600f
        findViewById<View>(R.id.header_bg).translationY = -600f
        findViewById<View>(R.id.bottom_navigation_container).translationY = 600f

        ObjectAnimator.ofFloat(findViewById(R.id.title), "translationY", 0f).apply {
            duration = 1000
            start()
        }

        ObjectAnimator.ofFloat(findViewById(R.id.header_bg), "translationY", 0f).apply {
            duration = 1000
            start()
        }

        ObjectAnimator.ofFloat(findViewById(R.id.bottom_navigation_container), "translationY", 0f).apply {
            duration = 1000
            start()
        }

    }

    fun showBackButton(fragment: Fragment) {

        findViewById<View>(R.id.main_btn_back).visibility = View.VISIBLE
        findViewById<View>(R.id.btn_back_bg).visibility = View.VISIBLE

        findViewById<CustomAppButton>(R.id.main_btn_back).setAction {
            replaceFragment(fragment)
            hideBackButton()
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

    fun replaceFragment(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

    }

    fun goToTitleScreen() {
        startActivity(Intent(this@MainActivity, TitleActivity::class.java))
        finish()
    }

}