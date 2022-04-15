package com.group4.habitpal.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.google.android.material.card.MaterialCardView
import com.group4.habitpal.R
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
class CustomAppButton : MaterialCardView {

    // keeps track of where user touches the screen
    private var touchX: Float = 0f
    private var touchY: Float = 0f

    // threshold for how far the user's finger can slide from
    // the original point of contact before cancelling the action
    private val cancelActionThreshold: Int = 100

    // determines if performClick action is to be called
    // if true, performClick is called
    // if false, performClick is not called
    private var isActive: Boolean = false

    // the action to be performed when performClick is called
    // displays a toast with the button's tag by default if there
    // is a tag defined
    var performAction: () -> Unit = fun() {
        if(this.tag != null) {
            Toast.makeText(context, this.tag.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    constructor(context: Context?) : super(context!!) {
        // sets the button's border color
        strokeColor = resources.getColor(R.color.buttonColorOutline, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        // sets the button's border color
        strokeColor = resources.getColor(R.color.buttonColorOutline, null)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        super.onTouchEvent(event)

        when (event.action) {

            // activates the button and keeps track of initial touch coordinates
            MotionEvent.ACTION_DOWN -> {

                touchX = event.rawX
                touchY = event.rawY

                visibility = View.INVISIBLE

                isActive = true

            }

            // performs the button's action if the action has not been cancelled
            MotionEvent.ACTION_UP -> {

                if(isActive) {
                    performClick()
                }

                // failsafe for buttons meant to disappear on click
                if(visibility != View.GONE) {
                    visibility = View.VISIBLE
                }

            }

            MotionEvent.ACTION_MOVE -> {

                // cancels click action if finger moves far enough away from original
                // point of contact
                if( isOutsideOfCancelThreshold(event.rawX, event.rawY)) {
                    cancelAction()
                }

            }

            MotionEvent.ACTION_CANCEL -> {
                cancelAction()
            }

        }

        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        performAction()
        return true
    }


    /**
     *
     * Assigns an action to the button.
     * The action is called whenever performClick is called.
     *
     * @param action the action being assigned to the button
     *
     * @author Sisiame B. Sakasamo
     *
     */
    fun setAction(action: () -> Unit) {
        performAction = action
    }

    /**
     *
     * Checks if the user's finger has been moved outside of the
     * button's action cancellation threshold.
     *
     * @param currentTouchX the x-coordinate of the current position of the user's finger on the screen
     * @param currentTouchY the y-coordinate of the current position of the user's finger on the screen
     *
     * @author Sisiame B. Sakasamo
     *
     */
    private fun isOutsideOfCancelThreshold(currentTouchX: Float, currentTouchY: Float) : Boolean {
        return abs(touchX - currentTouchX) + abs(touchY - currentTouchY) > cancelActionThreshold
    }

    /**
     *
     * Deactivates the button press and prevents onClick from being
     * called.
     *
     * @author Sisiame B. Sakasamo
     *
     */
    private fun cancelAction() {
        isActive = false
        visibility = View.VISIBLE
    }

}