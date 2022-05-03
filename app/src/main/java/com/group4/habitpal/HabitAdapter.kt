package com.group4.habitpal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group4.habitpal.activities.MainActivity
import com.group4.habitpal.fragments.HabitDetailFragment

class HabitAdapter(val context: Context, val habits:List<Habit>): RecyclerView.Adapter<HabitAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitAdapter.ViewHolder {
        //specify the layout file to use for this item
        val view = LayoutInflater.from(context).inflate(R.layout.item_habit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitAdapter.ViewHolder, position: Int) {
        val habit = habits[position]
        holder.bind(habit)
    }

    override fun getItemCount(): Int {
        return habits.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvType: TextView
        val tvDescription: TextView
        val tvProgress: TextView

        init {
            tvType = itemView.findViewById(R.id.tvType)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            tvProgress = itemView.findViewById(R.id.tvProgress)
        }

        fun bind(habit: Habit) {
            tvType.text = habit.getType()
            tvDescription.text = habit.getName()
            tvProgress.text = "0/" + habit.getGoal().toString()
        }
    }
}