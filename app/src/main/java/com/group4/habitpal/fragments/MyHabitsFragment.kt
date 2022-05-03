package com.group4.habitpal.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ViewUtils.getContentView
import com.group4.habitpal.Habit
import com.group4.habitpal.HabitAdapter
import com.group4.habitpal.R
import com.group4.habitpal.activities.MainActivity
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser


class MyHabitsFragment : Fragment() {

    lateinit var habitsRecyclerView: RecyclerView


    lateinit var adapter: HabitAdapter

    var allHabits: MutableList<Habit> = mutableListOf()

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

        //Recyclerview implementation
        habitsRecyclerView = view.findViewById(R.id.habitRecyclerView)

        adapter = HabitAdapter(requireContext(), allHabits)
        habitsRecyclerView.adapter = adapter
        habitsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        queryHabits()

//        val habitListIterator = habitsRecyclerView
//            .children
//            .iterator()
//
//        habitListIterator.forEach { v ->
//            v.setOnClickListener {
//                mainActivity.replaceFragment(HabitDetailFragment(null))
//            }
//        }

    }

    fun queryHabits() {
        // Define the class we would like to query
        val query: ParseQuery<Habit> = ParseQuery.getQuery(Habit::class.java)
        // Define our query conditions
        query.whereEqualTo("user", ParseUser.getCurrentUser())
        // Execute the find asynchronously
        query.findInBackground (object : FindCallback<Habit> {
            override fun done(habits: MutableList<Habit>?, e: ParseException?) {
                if (e != null) {
                    //something has gone wrong
                    Log.e(TAG, "Error fetching habits")

                } else {
                    if (habits != null) {
                        for (habit in habits) {
                            Log.i(
                                TAG,
                                "Habit: " + habit.getName())
                        }

                        allHabits.addAll(habits)
                        adapter.notifyDataSetChanged()
                        Log.i(
                            TAG,
                            "Habit: " + adapter.itemCount)

                    }
                }
            }
        }
        )
    }

    companion object {
        const val TAG = "MyHabitsFragment"
    }

}