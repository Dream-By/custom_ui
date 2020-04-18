package com.example.custom_ui

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kotlinx.android.parcel.Parcelize


class CustomView @JvmOverloads constructor(context: Context,
                                           attrs:AttributeSet? = null,
                                           defaultAttrs: Int = 0): AppCompatTextView(context, attrs,defaultAttrs) {



    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomView,0,0).apply {
            try {
                val shouldRevertText = getBoolean(R.styleable.CustomView_revertText,false)
                if (shouldRevertText){
                    val revertedText = text.reversed()
                    text = revertedText
                }
            } finally {
                recycle()
            }
        }

    }

        override fun onSaveInstanceState():Parcelable? {
            return SaveState(super.onSaveInstanceState(),text.toString())
            }

        override fun onRestoreInstanceState(state:Parcelable?){
            val savedState = state as SaveState
            text = savedState.text
            return super.onRestoreInstanceState(savedState.SuperState)
            }

        @Parcelize
        class SaveState(val SuperState:Parcelable?,val text:String?): Parcelable

}