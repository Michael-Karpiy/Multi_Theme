package com.multi_theme.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import com.multi_theme.FragmentEditCustomColor
import com.multi_theme.Item.ItemCustomColor
import com.multi_theme.Tools.ActivityMain
import com.multi_theme.R
import com.multi_theme.Tools.DatabaseAddCustomColor
import com.multi_theme.Tools.ThemeActivity

class AdapterCustomColor(var context: Context, private val itemCustomColor: ArrayList<ItemCustomColor>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_color -> { val view = LayoutInflater.from(context).inflate(R.layout.item_color, parent, false)
                MainViewHolder(view) }
            R.layout.item_addcolor -> { val view = LayoutInflater.from(context).inflate(R.layout.item_addcolor, parent, false)
                AddViewHolder(view) }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun getItemCount(): Int {
        return itemCustomColor.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_color -> (holder as MainViewHolder).bind(position)
            R.layout.item_addcolor -> (holder as AddViewHolder).bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCustomColor.size -> R.layout.item_addcolor
            else -> R.layout.item_color
        }
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cv1 = itemView.findViewById<CardView>(R.id.cv1)
        private val cv2 = itemView.findViewById<CardView>(R.id.cv2)
        private val cv3 = itemView.findViewById<CardView>(R.id.cv3)

        @SuppressLint("Range")
        fun bind(position: Int) {
            val p = itemCustomColor[position].colorPosition
            val databaseHelper = DatabaseAddCustomColor(context)
            val db = databaseHelper.writableDatabase
            @SuppressLint("Recycle") val c = db.rawQuery("SELECT * FROM CustomColor WHERE colorQuantity = '$p'", null)
            c.moveToNext()

            val color1 = itemView.findViewById<View>(R.id.view11)
            color1.setBackgroundColor(Color.parseColor(c.getString(c.getColumnIndex("colorBackgroundMainLight"))))

            val color2 = itemView.findViewById<View>(R.id.view12)
            color2.setBackgroundColor(Color.parseColor(c.getString(c.getColumnIndex("colorAppBarMain"))))

            val color3 = itemView.findViewById<View>(R.id.view21)
            color3.setBackgroundColor(Color.parseColor(c.getString(c.getColumnIndex("colorBackgroundSecondLight"))))

            val color4 = itemView.findViewById<View>(R.id.view22)
            color4.setBackgroundColor(Color.parseColor(c.getString(c.getColumnIndex("colorAccentBackground"))))

            val gspStandardAndroidTheme = context.getSharedPreferences("nameStandardAndroidTheme", AppCompatActivity.MODE_PRIVATE)
            val keyStandardAndroidTheme: Boolean = gspStandardAndroidTheme.getBoolean("keyStandardAndroidTheme", false)
            if (keyStandardAndroidTheme) {

                itemView.findViewById<CardView>(R.id.cv1).setCardBackgroundColor(Color.parseColor(ThemeActivity.colorActiveLight))
                itemView.findViewById<CardView>(R.id.cv2).setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundMainLight))
                itemView.findViewById<CardView>(R.id.cv3).setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))

                val gspMainColor = context.getSharedPreferences("nameColor", AppCompatActivity.MODE_PRIVATE)
                val positionColor: Int = gspMainColor.getInt("colorPosition", 0)
                val colorPosition = c.getInt(c.getColumnIndex("colorPosition"))

                if (positionColor ==  colorPosition) {
                    (cv2.layoutParams as? ViewGroup.MarginLayoutParams)?.setMargins(8,8,8,8)
                    cv2.requestLayout()

                    (cv3.layoutParams as? ViewGroup.MarginLayoutParams)?.setMargins(12,12,12,12)
                    cv3.requestLayout()
                }

                if (positionColor != colorPosition)  {
                    (cv2.layoutParams as? ViewGroup.MarginLayoutParams)?.setMargins(0,0,0,0)
                    cv2.requestLayout()

                    (cv3.layoutParams as? ViewGroup.MarginLayoutParams)?.setMargins(0,0,0,0)
                    cv3.requestLayout()
                }

                cv1.setOnClickListener {

                    val gspeCustomColor = context.getSharedPreferences("nameColor", AppCompatActivity.MODE_PRIVATE).edit()

                    gspeCustomColor.putInt("colorPosition", c.getInt(c.getColumnIndex("colorPosition")))

                    gspeCustomColor.putString("colorBackgroundMainLight", c.getString(c.getColumnIndex("colorBackgroundMainLight")))
                    gspeCustomColor.putString("colorBackgroundMainDark", c.getString(c.getColumnIndex("colorBackgroundMainDark")))

                    gspeCustomColor.putString("colorBackgroundSecondLight", c.getString(c.getColumnIndex("colorBackgroundSecondLight")))
                    gspeCustomColor.putString("colorBackgroundSecondDark", c.getString(c.getColumnIndex("colorBackgroundSecondDark")))

                    gspeCustomColor.putString("colorBackgroundThirdLight", c.getString(c.getColumnIndex("colorBackgroundThirdLight")))
                    gspeCustomColor.putString("colorBackgroundThirdDark", c.getString(c.getColumnIndex("colorBackgroundThirdDark")))

                    gspeCustomColor.putString("colorAppBarMain", c.getString(c.getColumnIndex("colorAppBarMain")))
                    gspeCustomColor.putString("colorAppBarSecond", c.getString(c.getColumnIndex("colorAppBarSecond")))

                    gspeCustomColor.putString("colorStatusBarMain", c.getString(c.getColumnIndex("colorStatusBarMain")))
                    gspeCustomColor.putString("colorStatusBarSecond", c.getString(c.getColumnIndex("colorStatusBarSecond")))

                    gspeCustomColor.putString("colorNavigationBarMain", c.getString(c.getColumnIndex("colorNavigationBarMain")))
                    gspeCustomColor.putString("colorNavigationBarSecond", c.getString(c.getColumnIndex("colorNavigationBarSecond")))

                    gspeCustomColor.putString("colorTextMainLight", c.getString(c.getColumnIndex("colorTextMainLight")))
                    gspeCustomColor.putString("colorTextMainDark", c.getString(c.getColumnIndex("colorTextMainDark")))

                    gspeCustomColor.putString("colorTextSecondLight", c.getString(c.getColumnIndex("colorTextSecondLight")))
                    gspeCustomColor.putString("colorTextSecondDark", c.getString(c.getColumnIndex("colorTextSecondDark")))

                    gspeCustomColor.putString("colorNoActiveLight", c.getString(c.getColumnIndex("colorNoActiveLight")))
                    gspeCustomColor.putString("colorNoActiveDark", c.getString(c.getColumnIndex("colorNoActiveDark")))

                    gspeCustomColor.putString("colorActiveLight", c.getString(c.getColumnIndex("colorActiveLight")))
                    gspeCustomColor.putString("colorActiveDark", c.getString(c.getColumnIndex("colorActiveDark")))

                    gspeCustomColor.putString("colorIconMainLight", c.getString(c.getColumnIndex("colorIconMainLight")))
                    gspeCustomColor.putString("colorIconMainDark", c.getString(c.getColumnIndex("colorIconMainDark")))

                    gspeCustomColor.putString("colorIconSecondLight", c.getString(c.getColumnIndex("colorIconSecondLight")))
                    gspeCustomColor.putString("colorIconSecondDark", c.getString(c.getColumnIndex("colorIconSecondDark")))

                    gspeCustomColor.putString("colorErrorBackground", c.getString(c.getColumnIndex("colorErrorBackground")))
                    gspeCustomColor.putString("colorErrorText", c.getString(c.getColumnIndex("colorErrorText")))
                    gspeCustomColor.putString("colorErrorIcon", c.getString(c.getColumnIndex("colorErrorIcon")))

                    gspeCustomColor.putString("colorAccentBackground", c.getString(c.getColumnIndex("colorAccentBackground")))
                    gspeCustomColor.putString("colorAccentText", c.getString(c.getColumnIndex("colorAccentText")))
                    gspeCustomColor.putString("colorAccentIcon", c.getString(c.getColumnIndex("colorAccentIcon")))

                    gspeCustomColor.apply()

                    getActivity(context)!!.recreate()
                }

                cv1.setOnLongClickListener{
                    val bundle = Bundle()
                    bundle.putString("position", c.getString(c.getColumnIndex("colorQuantity")))
                    (context as ActivityMain).showFragment(FragmentEditCustomColor()).arguments = bundle

                    return@setOnLongClickListener true
                }
            }
        }
    }

    inner class AddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cv1 = itemView.findViewById<CardView>(R.id.cv1)
        private val cv2 = itemView.findViewById<CardView>(R.id.cv2)
        private val cv3 = itemView.findViewById<CardView>(R.id.cv3)
        private val iv = itemView.findViewById<ImageView>(R.id.iv)

        fun bind() {

            val gspStandardAndroidTheme = context.getSharedPreferences("nameStandardAndroidTheme", AppCompatActivity.MODE_PRIVATE)
            val keyStandardAndroidTheme: Boolean = gspStandardAndroidTheme.getBoolean("keyStandardAndroidTheme", false)
            if (keyStandardAndroidTheme) {

                cv1.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))
                cv2.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundMainDark))
                cv3.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))
                iv.setColorFilter(Color.parseColor(ThemeActivity.colorTextMainLight))

                cv1.setOnClickListener {

                    val databaseAddCustomColor = DatabaseAddCustomColor(context)
                    databaseAddCustomColor.addCustomColor(
                        ThemeActivity.quantityStandardColor + 1,

                        ThemeActivity.colorBackgroundMainLight,
                        ThemeActivity.colorBackgroundMainDark,

                        ThemeActivity.colorBackgroundSecondLight,
                        ThemeActivity.colorBackgroundSecondDark,

                        ThemeActivity.colorBackgroundThirdLight,
                        ThemeActivity.colorBackgroundThirdDark,

                        ThemeActivity.colorAppBarMain,
                        ThemeActivity.colorAppBarSecond,

                        ThemeActivity.colorStatusBarMain,
                        ThemeActivity.colorStatusBarSecond,

                        ThemeActivity.colorNavigationBarMain,
                        ThemeActivity.colorNavigationBarSecond,

                        ThemeActivity.colorTextMainLight,
                        ThemeActivity.colorTextMainDark,

                        ThemeActivity.colorTextSecondLight,
                        ThemeActivity.colorTextSecondDark,

                        ThemeActivity.colorNoActiveLight,
                        ThemeActivity.colorNoActiveDark,

                        ThemeActivity.colorActiveLight,
                        ThemeActivity.colorActiveDark,

                        ThemeActivity.colorIconMainLight,
                        ThemeActivity.colorIconMainDark,

                        ThemeActivity.colorIconSecondLight,
                        ThemeActivity.colorIconSecondDark,

                        ThemeActivity.colorErrorBackground,
                        ThemeActivity.colorErrorText,
                        ThemeActivity.colorErrorIcon,

                        ThemeActivity.colorAccentBackground,
                        ThemeActivity.colorAccentText,
                        ThemeActivity.colorAccentIcon
                    )
                    getActivity(context)!!.recreate()
                }
            }
        }
    }
}