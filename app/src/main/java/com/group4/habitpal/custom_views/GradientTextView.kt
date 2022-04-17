package com.group4.habitpal.custom_views

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.group4.habitpal.R


class GradientTextView : AppCompatTextView {
    constructor(context: Context?) : super(context!!, null, -1) {}
    constructor(
        context: Context?,
        attrs: AttributeSet?
    ) : super(context!!, attrs, -1)

    constructor(
        context: Context?,
        attrs: AttributeSet?, defStyle: Int
    ) : super(context!!, attrs, defStyle)

    override fun onLayout(
        changed: Boolean,
        left: Int, top: Int, right: Int, bottom: Int
    ) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            paint.shader = LinearGradient(
                0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(),
                resources.getColor(R.color.colorPrimary2, null),
                resources.getColor(R.color.colorPrimary, null),
                Shader.TileMode.CLAMP
            )
        }
    }
}