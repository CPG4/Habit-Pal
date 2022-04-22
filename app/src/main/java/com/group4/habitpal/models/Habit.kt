package com.group4.habitpal.models

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

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

@ParseClassName("Habit")
class Habit : ParseObject() {

    fun getId(): String? {
        return getString(KEY_ID)
    }

    fun setId(id: String) {
        put(KEY_ID, id)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)

    }

    fun setUser(user: ParseUser) {
        put(KEY_USER, user)
    }

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

    fun getReminders(): Boolean? {
        return getBoolean(KEY_REMINDERS)

    }

    fun setReminders(reminders: Boolean) {
        put(KEY_REMINDERS, reminders)
    }

    fun getGoal(): Int? {
        return getInt(KEY_GOAL)
    }

    fun setGoal(goal: Int) {
        put(KEY_GOAL, goal)
    }

    fun getMilestone(): Int? {
        return getInt(KEY_MILESTONE)
    }

    fun setMilestone(milestone: Int) {
        put(KEY_MILESTONE, milestone)
    }

    fun getImage(): ParseFile? {
        return getParseFile(KEY_IMAGE)

    }

    fun setImage(image: ParseFile) {
        put(KEY_IMAGE, image)
    }

    fun getPoints(): Int? {
        return getInt(KEY_POINTS)
    }

    fun setPoints(points: Int) {
        put(KEY_POINTS, points)
    }



    companion object {
        const val KEY_ID = "objectId"
        const val KEY_USER = "user"
        const val KEY_NAME = "name"
        const val KEY_TYPE = "type"
        const val KEY_REMINDERS = "reminders"
        const val KEY_GOAL = "goal"
        const val KEY_MILESTONE = "milestone"
        const val KEY_IMAGE = "image"
        const val KEY_POINTS = "points"

    }



    // TODO: fields, constructors, functions, javadoc
}