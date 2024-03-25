package com.multi_theme.Tools

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.multi_theme.Tools.ThemeActivity.Companion.CORNER_RADIUS
import com.multi_theme.Tools.ThemeActivity.Companion.mCornerRadius

class CornerCardView: CardView {

    private var mPaint: Paint? = null
    private var mMaskPaint: Paint? = null
    private var mMetrics: DisplayMetrics? = null
    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,attrs,defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        mMetrics = context.resources.displayMetrics
        mCornerRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, CORNER_RADIUS, mMetrics)
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mMaskPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        mMaskPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        setWillNotDraw(false)
    }

    override fun draw(canvas: Canvas) {
        if (isInEditMode) {
            super.draw(canvas)
        }
        val offscreenBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val offscreenCanvas = Canvas(offscreenBitmap)
        super.draw(offscreenCanvas)
        val maskBitmap = createMask(width, height)
        offscreenCanvas.drawBitmap(maskBitmap, 0f, 0f, mMaskPaint)
        canvas.drawBitmap(offscreenBitmap, 0f, 0f, mPaint)
    }

    private fun createMask(width: Int, height: Int): Bitmap {
        val gspCornerRadius = context.getSharedPreferences("nameCornerRadius", AppCompatActivity.MODE_PRIVATE)
        val sliderValue = gspCornerRadius.getFloat("keyCornerRadius", 0.0f)

        val mask = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8)
        val tempCanvas = Canvas(mask)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.WHITE
        tempCanvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        tempCanvas.drawRoundRect(
            RectF(0F, 0F, width.toFloat(), height.toFloat()),
            sliderValue*3,
            sliderValue*3,
            paint
        )
        return mask
    }

    fun setCornerRadius(myCornerRadius: Float) {
        CORNER_RADIUS = myCornerRadius
        mCornerRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, CORNER_RADIUS, mMetrics)
        this.invalidate()
    }
}