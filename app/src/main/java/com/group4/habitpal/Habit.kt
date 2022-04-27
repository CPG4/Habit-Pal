package com.group4.habitpal

/*
 *
 * Description
 *
 * Type (habit to form or quit)
 *
 * Details:
 * start date
 * end goal
 *
 * Stats:
 * days complete
 * days missed
 * highest completion streak
 *
 * Progress:
 * week summary
 * month summary
 * quarter summary (3 months)
 * mid-year summary (6 months)
 * year summary
 *
 */

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

@ParseClassName("Habit")
class Habit : ParseObject() {

    fun getName(): String? {
        return getString(KEY_NAME)
    }

    fun setName(name: String) {
        put(KEY_NAME, name)
    }

    fun getType(): String? {
        return getString(KEY_TYPE)
    }

    fun setType(type: String) {
        put(KEY_TYPE, type)
    }

    fun getGoal(): Number? {
        return getNumber(KEY_GOAL)
    }

    fun setGoal(goal: Number) {
        put(KEY_GOAL, goal)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }

    fun setUser(user: ParseUser) {
        put(KEY_USER, user)
    }

    companion object{
        const val KEY_NAME = "name"
        const val KEY_TYPE = "type"
        const val KEY_GOAL = "goal"
        const val KEY_USER = "user"
    }

}