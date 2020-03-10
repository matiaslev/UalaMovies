package com.matiaslev.ualamovies.presentation

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.matiaslev.ualamovies.R
import kotlinx.android.synthetic.main.uala_animated_text.view.a1
import kotlinx.android.synthetic.main.uala_animated_text.view.a2
import kotlinx.android.synthetic.main.uala_animated_text.view.l
import kotlinx.android.synthetic.main.uala_animated_text.view.u

class CustomUalaAnimatedText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.uala_animated_text, this)
        scaleTextsOnX()
        scaleTextsOnY()
        setOnClickListener {
            rotateView()
        }
    }

    private fun scaleTextsOnX() {
        ObjectAnimator.ofFloat(u, "scaleX", 2f).apply {
            duration = 2000
            start()
        }
        ObjectAnimator.ofFloat(a1, "scaleX", 2f).apply {
            duration = 4000
            start()
        }
        ObjectAnimator.ofFloat(l, "scaleX", 2f).apply {
            duration = 6000
            start()
        }
        ObjectAnimator.ofFloat(a2, "scaleX", 2f).apply {
            duration = 8000
            start()
        }
    }

    private fun scaleTextsOnY() {
        ObjectAnimator.ofFloat(u, "scaleY", 2f).apply {
            duration = 2000
            start()
        }
        ObjectAnimator.ofFloat(a1, "scaleY", 2f).apply {
            duration = 4000
            start()
        }
        ObjectAnimator.ofFloat(l, "scaleY", 2f).apply {
            duration = 6000
            start()
        }
        ObjectAnimator.ofFloat(a2, "scaleY", 2f).apply {
            duration = 8000
            start()
        }
    }

    private fun rotateView() {
        ObjectAnimator.ofFloat(this, "rotation", 0f, 360f).apply {
            duration = 4000
            start()
        }
    }
}