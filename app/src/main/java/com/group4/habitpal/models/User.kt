package com.group4.habitpal.models

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseUser
import java.time.LocalDate
import java.util.*


@ParseClassName("User")
class User : ParseUser() {

    fun getId(): String? {
        return getString(KEY_ID)
    }

    fun setId(id: String) {
        put(KEY_ID, id)
    }

    override fun getUsername(): String? {
        return getString(KEY_USERNAME)
    }

    override fun setUsername(username: String) {
        put(KEY_USERNAME, username)
    }

    override fun getEmail(): String? {
        return getString(KEY_EMAIL)
    }

    override fun setEmail(email: String) {
        put(KEY_EMAIL, email)
    }

    fun getPassword(): String? {
        return getString(KEY_PASSWORD)
    }

    override fun setPassword(password: String) {
        put(KEY_PASSWORD, password)
    }

    fun getFirstName(): String? {
        return getString(KEY_FIRST_NAME)
    }

    fun setFirstName(first_name: String) {
        put(KEY_FIRST_NAME, first_name)
    }

    fun getLastName(): String? {
        return getString(KEY_LAST_NAME)
    }

    fun setLastName(last_name: String) {
        put(KEY_LAST_NAME, last_name)
    }

    fun getPhoneNumber(): String? {
        return getString(KEY_PHONE_NUMBER)
    }

    fun setPhoneNumber(phone_number: String) {
        put(KEY_PHONE_NUMBER, phone_number)
    }

    fun getDateOfBirth(): Date? {
        return getDate(KEY_DATE_OF_BIRTH)

    }

    fun setDateOfBirth(date_of_birth: Date) {
        put(KEY_DATE_OF_BIRTH, date_of_birth)
    }

    fun getAvatar(): ParseFile? {
        return getParseFile(KEY_AVATAR)

    }

    fun setAvatar(avatar: ParseFile) {
        put(KEY_AVATAR, avatar)
    }

    fun getHabitScore(): Int? {
        return getInt(KEY_HABIT_SCORE)
    }

    fun setHabitScore(habit_score: Int) {
        put(KEY_HABIT_SCORE, habit_score)
    }




    companion object {
        const val KEY_ID = "objectId"
        const val KEY_USERNAME="username"
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD = "password"
        const val KEY_FIRST_NAME = "first_name"
        const val KEY_LAST_NAME = "last_name"
        const val KEY_PHONE_NUMBER = "phone_number"
        const val KEY_DATE_OF_BIRTH = "date_of_birth"
        const val KEY_AVATAR = "avatar"
        const val KEY_HABIT_SCORE = "habit_score"


    }

}