package com.multi_theme.Tools

import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import kotlin.jvm.internal.Intrinsics

class FadeVisibility {

    companion object {
        fun fadeVisibility(view: View, visibility: Int, duration: Long) {
            Intrinsics.checkNotNullParameter(view, "\$this\$fadeVisibility")
            val transition: Transition = Fade()
            transition.duration = duration
            transition.addTarget(view)
            val var10000 = view.parent
            if (var10000 == null) {
                throw NullPointerException("null cannot be cast to non-null type android.view.ViewGroup")
            } else {
                TransitionManager.beginDelayedTransition(var10000 as ViewGroup, transition)
                view.visibility = visibility
            }
        }
    }
}