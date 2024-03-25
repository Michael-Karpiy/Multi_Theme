package com.multi_theme.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.multi_theme.Item.ItemPalette
import com.multi_theme.R
import com.multi_theme.Tools.ThemeActivity

class AdapterPalette(private val context: Context, private val itemColor: List<ItemPalette>) :
    RecyclerView.Adapter<AdapterPalette.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind() {
            itemView.findViewById<Button>(R.id.cv0)
            itemView.findViewById<Button>(R.id.cv1)
            itemView.findViewById<TextView>(R.id.tv1)
            itemView.findViewById<TextView>(R.id.tv1)

            val gspStandardAndroidTheme = context.getSharedPreferences("nameStandardAndroidTheme", AppCompatActivity.MODE_PRIVATE)
            val statusSAT: Boolean = gspStandardAndroidTheme.getBoolean("keyStandardAndroidTheme", false)
            if (statusSAT) {

                itemView.findViewById<CardView>(R.id.cv0).setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))
                itemView.findViewById<CardView>(R.id.cv2).setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))
                itemView.findViewById<TextView>(R.id.tv1).setTextColor(Color.parseColor(ThemeActivity.colorTextMainLight))
                itemView.findViewById<TextView>(R.id.tv2).setTextColor(Color.parseColor(ThemeActivity.colorTextSecondLight))

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pallete, parent, false))
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemColor[position]
        holder.bind()

        try {
            holder.itemView.findViewById<CardView>(R.id.cv1).setCardBackgroundColor(Color.parseColor(item.colorId))
        } catch (e: IllegalArgumentException) {
            holder.itemView.findViewById<CardView>(R.id.cv1).setCardBackgroundColor(Color.TRANSPARENT)
        }

        holder.itemView.findViewById<TextView>(R.id.tv1).text = item.colorName
        holder.itemView.findViewById<TextView>(R.id.tv2).text = item.colorId

    }

    override fun getItemCount(): Int {
        return itemColor.size
    }
}