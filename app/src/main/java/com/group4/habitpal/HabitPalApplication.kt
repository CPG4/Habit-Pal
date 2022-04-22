package com.group4.habitpal

import android.app.Application
import com.group4.habitpal.models.Habit
import com.group4.habitpal.models.User
import com.parse.Parse
import com.parse.ParseObject

class HabitPalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ParseObject.registerSubclass(User::class.java)
        ParseObject.registerSubclass(Habit::class.java)
        Parse.initialize(Parse.Configuration.Builder(this).applicationId(getString(R.string.back4app_app_id)).clientKey(getString(R.string.back4app_client_key)).server(getString(R.string.back4app_server_url)).build());



    }

}