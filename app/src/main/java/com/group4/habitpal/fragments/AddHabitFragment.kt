package com.group4.habitpal.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import com.group4.habitpal.Habit
import com.group4.habitpal.R
import com.group4.habitpal.activities.MainActivity
import com.group4.habitpal.activities.TitleActivity
import com.group4.habitpal.custom_views.CustomAppButton
import com.parse.ParseUser

class AddHabitFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addhabit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Form/Break habit button controls:

        //Habit text:
        val habitText: TextView = view.findViewById<TextView>(R.id.text_type)
        val habitDesc: TextView = view.findViewById<TextView>(R.id.text_description)
        var formOrBreak: String = "form"

        //Form:
        val btnForm: Button = view.findViewById<Button>(R.id.text_form)

        //Break:
        val btnBreak: Button = view.findViewById<Button>(R.id.text_break)

        //Form onClick:
        btnForm.setOnClickListener {
            habitText.text = resources.getText(R.string.habit_type_header_form)
            habitDesc.text = "I want to start..."
            formOrBreak = "Form"
        }

        //Break onClick:
        btnBreak.setOnClickListener {
            habitText.text = resources.getText(R.string.habit_type_header_break)
            habitDesc.text = "I want to stop..."
            formOrBreak = "Break"
        }


        // get data that user has inputted
        // for now it will only be habits to form, and goal will be 21 days
        view.findViewById<CustomAppButton>(R.id.btn_add).setOnClickListener {
            val name = view.findViewById<EditText>(R.id.field_description).text.toString()
            val user = ParseUser.getCurrentUser()
            val type = formOrBreak
            val goal = 21
            submitPost(name, user, type, goal)
            view.findViewById<CustomAppButton>(R.id.btn_add).isEnabled = false
            val bottomNav = (activity as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav.selectedItemId = R.id.action_myhabits
        }
    }

    //send a Habit object to our parse server
    private fun submitPost(name: String, user: ParseUser, type: String, goal: Int) {

        //create the Habit object
        Log.i(TAG, "SubmitPost function started...")
        val habit = Habit()
        habit.setName(name)
        habit.setUser(user)
        habit.setType(type)
        habit.setGoal(goal)
        habit.saveInBackground { exception ->
            if (exception != null){
                //something has went wrong
                Log.e(TAG, "Error while saving post")
                exception.printStackTrace()
                Toast.makeText(requireContext(), "Error saving habit!", Toast.LENGTH_SHORT).show()
            } else {
                Log.i(TAG, "Successfully saved habit")
                val mainActivity = requireActivity() as MainActivity
                mainActivity.replaceFragment(MyHabitsFragment())
            }
        }
    }

    companion object {
        const val TAG = "AddHabitFragment"
    }
}