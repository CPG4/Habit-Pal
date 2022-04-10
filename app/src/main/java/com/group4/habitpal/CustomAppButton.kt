package com.group4.habitpal

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
import kotlin.math.abs

/**
 *
 * To be used for app buttons.
 * Note that every button should have a CustomAppButton as a foreground for touch
 * events, and a CardView as a background for aesthetic purposes.
 *
 * @author Sisiame B. Sakasamo
 *
 */
class CustomAppButton : CardView {

    private var touchX: Float = 0f
    private var touchY: Float = 0f
    private var isActive: Boolean = false
    var performAction: () -> Unit = fun() {
        Toast.makeText(context, this.tag.toString(), Toast.LENGTH_SHORT).show()
    }

    constructor(context: Context?) : super(context!!)

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    override fun onTouchEvent(event: MotionEvent): Boolean {

        super.onTouchEvent(event)

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {

                touchX = event.rawX
                touchY = event.rawY

                visibility = View.INVISIBLE

                isActive = true

            }

            MotionEvent.ACTION_UP -> {

                if(isActive) {
                    performClick()
                }

                visibility = View.VISIBLE

            }

            MotionEvent.ACTION_MOVE -> {

                if(abs(touchX - event.rawX) > 100 || abs(touchY - event.rawY) > 100) {
                    isActive = false
                    visibility = View.VISIBLE
                }

            }

            MotionEvent.ACTION_CANCEL -> {
                isActive = false
                visibility = View.VISIBLE
            }

        }

        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        performAction()
        return true
    }

    fun setAction(action: () -> Unit) {
        performAction = action
    }

}