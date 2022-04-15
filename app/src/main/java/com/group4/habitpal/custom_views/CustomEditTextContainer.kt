package com.group4.habitpal.custom_views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewParent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.widget.ContentFrameLayout
import com.google.android.material.card.MaterialCardView


/**
 *
 * To be used for edit text containers.
 * Note that every edit text should have a CustomEditTextContainer as a
 * container.
 *
 * @author Sisiame B. Sakasamo
 *
 */
class CustomEditTextContainer : MaterialCardView {

   private lateinit var editText : EditText
   private lateinit var screen : View

    constructor(context: Context?) : super(context!!) {
        setCardBackgroundColor(ColorStateList.valueOf(0x00000000))
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        setCardBackgroundColor(ColorStateList.valueOf(0x00000000))

    }

    override fun onViewAdded(child: View?) {

        super.onViewAdded(child)

        editText = child as EditText

        editText.onFocusChangeListener = OnFocusChangeListener { _: View, focused: Boolean ->

            cardElevation =
                if(focused) {
                    10f
                } else {
                    0f
                }

        }
        
        editText.setOnEditorActionListener { _, action, _ ->

            if(action == EditorInfo.IME_ACTION_DONE) {
                editText.clearFocus()
            }

            false
        }

    }

}