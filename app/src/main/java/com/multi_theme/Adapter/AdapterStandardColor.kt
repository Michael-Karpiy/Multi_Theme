package com.multi_theme.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import com.multi_theme.Item.ItemStandardColor
import com.multi_theme.R
import com.multi_theme.Tools.ThemeActivity

class AdapterStandardColor(private val context: Context, private val itemColor: List<ItemStandardColor>) :
    RecyclerView.Adapter<AdapterStandardColor.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var cvCheck: CardView

        fun bind(itemColor: ItemStandardColor) {
            val color1 = itemView.findViewById<View>(R.id.view11)
            color1.setBackgroundColor(Color.parseColor(itemColor.colorBackgroundMainLight))

            val color2 = itemView.findViewById<View>(R.id.view12)
            color2.setBackgroundColor(Color.parseColor(itemColor.colorAppBarMain))

            val color3 = itemView.findViewById<View>(R.id.view21)
            color3.setBackgroundColor(Color.parseColor(itemColor.colorBackgroundSecondLight))

            val color4 = itemView.findViewById<View>(R.id.view22)
            color4.setBackgroundColor(Color.parseColor(itemColor.colorAccentBackground))

            cvCheck = itemView.findViewById(R.id.cvCheck)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_color, parent, false))
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemColor[position]
        holder.bind(item)

        val gspStandardAndroidTheme = context.getSharedPreferences("nameStandardAndroidTheme", AppCompatActivity.MODE_PRIVATE)
        val keyStandardAndroidTheme: Boolean = gspStandardAndroidTheme.getBoolean("keyStandardAndroidTheme", false)
        if (keyStandardAndroidTheme) {
            holder.itemView.findViewById<CardView>(R.id.cv1).setCardBackgroundColor(Color.parseColor(ThemeActivity.colorActiveLight))
            holder.itemView.findViewById<CardView>(R.id.cv2).setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundMainLight))
            holder.itemView.findViewById<CardView>(R.id.cv3).setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))

            val gspMainColor = context.getSharedPreferences("nameColor", AppCompatActivity.MODE_PRIVATE)
            val positionColor: Int = gspMainColor.getInt("colorPosition", 0)

            if (positionColor == item.colorPosition) {
                val cv2 = holder.itemView.findViewById<CardView>(R.id.cv2)
                (cv2.layoutParams as? ViewGroup.MarginLayoutParams)?.setMargins(8,8,8,8)
                cv2.requestLayout()

                val cv3 = holder.itemView.findViewById<CardView>(R.id.cv3)
                (cv3.layoutParams as? ViewGroup.MarginLayoutParams)?.setMargins(12,12,12,12)
                cv3.requestLayout()
            }

            if (positionColor != item.colorPosition) {
                val cv2 = holder.itemView.findViewById<CardView>(R.id.cv2)
                (cv2.layoutParams as? ViewGroup.MarginLayoutParams)?.setMargins(0,0,0,0)
                cv2.requestLayout()

                val cv3 = holder.itemView.findViewById<CardView>(R.id.cv3)
                (cv3.layoutParams as? ViewGroup.MarginLayoutParams)?.setMargins(0,0,0,0)
                cv3.requestLayout()
            }

            val cvItemColor = holder.itemView.findViewById<CardView>(R.id.cv1)
            cvItemColor.setOnClickListener {

                val gspeMainColor = context.getSharedPreferences("nameColor", AppCompatActivity.MODE_PRIVATE).edit()

                gspeMainColor.putInt("colorPosition", item.colorPosition)

                gspeMainColor.putString("colorBackgroundMainLight", item.colorBackgroundMainLight)
                gspeMainColor.putString("colorBackgroundMainDark", item.colorBackgroundMainDark)

                gspeMainColor.putString("colorBackgroundSecondLight", item.colorBackgroundSecondLight)
                gspeMainColor.putString("colorBackgroundSecondDark", item.colorBackgroundSecondDark)

                gspeMainColor.putString("colorBackgroundThirdLight", item.colorBackgroundThirdLight)
                gspeMainColor.putString("colorBackgroundThirdDark", item.colorBackgroundThirdDark)

                gspeMainColor.putString("colorAppBarMain", item.colorAppBarMain)
                gspeMainColor.putString("colorAppBarSecond", item.colorAppBarSecond)

                gspeMainColor.putString("colorStatusBarMain", item.colorStatusBarMain)
                gspeMainColor.putString("colorStatusBarSecond", item.colorStatusBarSecond)

                gspeMainColor.putString("colorNavigationBarMain", item.colorNavigationBarMain)
                gspeMainColor.putString("colorNavigationBarSecond", item.colorNavigationBarSecond)

                gspeMainColor.putString("colorTextMainLight", item.colorTextMainLight)
                gspeMainColor.putString("colorTextMainDark", item.colorTextMainDark)

                gspeMainColor.putString("colorTextSecondLight", item.colorTextSecondLight)
                gspeMainColor.putString("colorTextSecondDark", item.colorTextSecondDark)

                gspeMainColor.putString("colorNoActiveLight", item.colorNoActiveLight)
                gspeMainColor.putString("colorNoActiveDark", item.colorNoActiveDark)

                gspeMainColor.putString("colorActiveLight", item.colorActiveLight)
                gspeMainColor.putString("colorActiveDark", item.colorActiveDark)

                gspeMainColor.putString("colorIconMainLight", item.colorIconMainLight)
                gspeMainColor.putString("colorIconMainDark", item.colorIconMainDark)

                gspeMainColor.putString("colorIconSecondLight", item.colorIconSecondLight)
                gspeMainColor.putString("colorIconSecondDark", item.colorIconSecondDark)

                gspeMainColor.putString("colorErrorBackground", item.colorErrorBackground)
                gspeMainColor.putString("colorErrorText", item.colorErrorText)
                gspeMainColor.putString("colorErrorIcon", item.colorErrorIcon)

                gspeMainColor.putString("colorAccentBackground", item.colorAccentBackground)
                gspeMainColor.putString("colorAccentText", item.colorAccentText)
                gspeMainColor.putString("colorAccentIcon", item.colorAccentIcon)

                gspeMainColor.apply()

                getActivity(context)!!.recreate()
            }
        }
    }

    override fun getItemCount(): Int {
        return itemColor.size
    }
}