package com.group4.habitpal.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.group4.habitpal.Habit
import com.group4.habitpal.R
import com.group4.habitpal.activities.MainActivity
import com.parse.ParseQuery
import com.parse.ParseUser


class MyHabitsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myhabits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = requireActivity() as MainActivity

        val habitListIterator = mainActivity
            .findViewById<LinearLayout>(R.id.habitlist)
            .children
            .iterator()

        habitListIterator.forEach { v ->
            v.setOnClickListener {
                mainActivity.replaceFragment(HabitDetailFragment(null))
            }
        }

        // Define the class we would like to query
        val query: ParseQuery<Habit> = ParseQuery.getQuery(Habit::class.java)
        // Define our query conditions
        query.whereEqualTo("user", ParseUser.getCurrentUser())
        // Execute the find asynchronously
        query.findInBackground { itemList, e ->
            if (e == null) {
                // Access the array of results here
                val items: List<Habit> = itemList
                Log.i(TAG, "First habit: " + items[0].getName())
            } else {
                Log.d("item", "Error: " + e.printStackTrace())
            }
        }
    }

    companion object {
        const val TAG = "MyHabitsFragment"
    }

}