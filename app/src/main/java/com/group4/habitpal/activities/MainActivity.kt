package com.group4.habitpal.activities

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.group4.habitpal.R
import com.group4.habitpal.fragments.*


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


        bottomNav = findViewById(R.id.bottom_navigation)

        bottomNav.setOnItemSelectedListener { item ->

            when(item.itemId) {

                R.id.action_myhabits -> {
                    replaceFragment(MyHabitsFragment())
                }

                R.id.action_addhabit -> {
                    replaceFragment(AddHabitFragment())
                }

                R.id.action_home -> {
                    replaceFragment(SettingsFragment())
                }

                R.id.action_feed -> {
                    replaceFragment(MyHabitsFragment())
                }

                R.id.action_profile -> {
                    replaceFragment(ProfileFragment())
                }

            }

            true

        }

        bottomNav.selectedItemId = R.id.action_home

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