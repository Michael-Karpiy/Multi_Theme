package com.multi_theme

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.multi_theme.Tools.DatabaseAddCustomColor
import com.multi_theme.Tools.ThemeActivity

class DialogEditCustomColor : DialogFragment() {

    private lateinit var cvBack: CardView
    private lateinit var ivBack: ImageView

    private lateinit var cvAccept: CardView
    private lateinit var ivAccept: ImageView

    private lateinit var tv0: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvNote: TextView
    private lateinit var et1: EditText

    private lateinit var cv1: CardView
    private lateinit var cv2: CardView
    private lateinit var cvColor: CardView

    private lateinit var cardView: CardView

    private lateinit var tvError: TextView
    private lateinit var cvError: CardView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_edittext, container, false)
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        cvBack = requireView().findViewById(R.id.cvBack)
        ivBack = requireView().findViewById(R.id.ivBack)

        cvBack.setOnClickListener { dismiss()}

        cvAccept = requireView().findViewById(R.id.cvAccept)
        ivAccept = requireView().findViewById(R.id.ivAccept)

        tv0 = requireView().findViewById(R.id.tv0)
        tvTitle = requireView().findViewById(R.id.tvTitle)
        tvNote = requireView().findViewById(R.id.tvNote)
        et1 = requireView().findViewById(R.id.et1)

        cv1 = requireView().findViewById(R.id.cv1)
        cv2 = requireView().findViewById(R.id.cv2)
        cvColor = requireView().findViewById(R.id.cvColor)

        cardView = requireView().findViewById(R.id.cardView)

        tvError = requireView().findViewById(R.id.tvError)
        cvError = requireView().findViewById(R.id.cvError)

        val colorName = requireArguments().getString("colorName")
        val colorId = requireArguments().getString("colorId")
        val colorPosition = requireArguments().getInt("colorPosition")

        tvTitle.text = colorName
        et1.setText(colorId!!.replace("#", "")).toString()

        cvColor.setCardBackgroundColor(Color.parseColor("#" + et1.text.toString()))
        et1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                try {
                    cvColor.setCardBackgroundColor(Color.parseColor("#" + et1.text.toString()))
                    cvColor.visibility = View.VISIBLE
                    cvError.visibility = View.GONE
                    tvError.visibility = View.GONE

                    cvAccept.setOnClickListener {
                        val b = Bundle()
                        b.putString("et", "#" + et1.text.toString())
                        setFragmentResult("requestKey", b)

                        val databaseAddCustomColor = DatabaseAddCustomColor(context)
                        databaseAddCustomColor.editSpecificData(colorPosition, colorName, "#" + et1.text.toString())

                        val gspeCustomColor = requireContext().getSharedPreferences("nameColor", AppCompatActivity.MODE_PRIVATE).edit()
                        gspeCustomColor.putString("colorBackgroundMain", "#" +  et1.text.toString())
                        gspeCustomColor.apply()

                        dismiss()
                    }
                } catch (e: IllegalArgumentException) {
                    cvColor.visibility = View.GONE
                    cvError.visibility = View.VISIBLE
                    tvError.visibility = View.VISIBLE
                }
            }
        })
        applyingColor()
    }

    private fun applyingColor() {

        val gspStandardAndroidTheme = requireContext().getSharedPreferences("nameStandardAndroidTheme", AppCompatActivity.MODE_PRIVATE)
        val statusSAT: Boolean = gspStandardAndroidTheme.getBoolean("keyStandardAndroidTheme", false)
        if (statusSAT) {

            cvAccept.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))
            ivAccept.setColorFilter(Color.parseColor(ThemeActivity.colorTextMainLight))

            cvBack.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))
            ivBack.setColorFilter(Color.parseColor(ThemeActivity.colorTextMainLight))

            tv0.setTextColor(Color.parseColor(ThemeActivity.colorTextSecondLight))
            tvTitle.setTextColor(Color.parseColor(ThemeActivity.colorTextMainLight))
            tvNote.setTextColor(Color.parseColor(ThemeActivity.colorTextSecondLight))

            et1.setTextColor(Color.parseColor(ThemeActivity.colorTextMainLight))
            cv1.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))
            cv2.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))
            cardView.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))

            cvError.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorErrorBackground))
            tvError.setTextColor(Color.parseColor(ThemeActivity.colorErrorText))
        }
    }

    companion object {
        fun newInstance(): DialogEditCustomColor {
            return DialogEditCustomColor()
        }
    }
}