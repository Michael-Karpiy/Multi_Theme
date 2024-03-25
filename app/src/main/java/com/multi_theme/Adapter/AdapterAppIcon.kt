package com.multi_theme.Adapter

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.multi_theme.R
import com.multi_theme.Tools.ThemeActivity
import com.multi_theme.Item.ItemAppIcon

class AdapterAppIcon(private val context: Context, private val itemColor: List<ItemAppIcon>) :
    RecyclerView.Adapter<AdapterAppIcon.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var cvCheck: CardView

        fun bind(item: ItemAppIcon) {
            val cv3 = itemView.findViewById<View>(R.id.iv)
            if (item.keyAppIcon == 1){
                cv3.setBackgroundResource(R.mipmap.ic_launcher_green)
            }

            if (item.keyAppIcon == 2){
                cv3.setBackgroundResource(R.mipmap.ic_launcher_black)
            }

            if (item.keyAppIcon == 3){
                cv3.setBackgroundResource(R.mipmap.ic_launcher_wight)
            }
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
        val statusSAT: Boolean = gspStandardAndroidTheme.getBoolean("keyStandardAndroidTheme", false)
        if (statusSAT) {
            holder.itemView.findViewById<CardView>(R.id.cv1).setCardBackgroundColor(Color.parseColor(ThemeActivity.colorActiveLight))
            holder.itemView.findViewById<CardView>(R.id.cv2).setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundMainLight))
            holder.itemView.findViewById<CardView>(R.id.cv3).setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))
        }

        val gspAppIcon = context.getSharedPreferences("nameAppIcon", AppCompatActivity.MODE_PRIVATE)
        val numberAppIcon: Int = gspAppIcon.getInt("keyAppIcon", 1)

        if (item.keyAppIcon == numberAppIcon) {
            val cv2 = holder.itemView.findViewById<CardView>(R.id.cv2)
            (cv2.layoutParams as? ViewGroup.MarginLayoutParams)?.setMargins(8,8,8,8)
            cv2.requestLayout()

            val cv3 = holder.itemView.findViewById<CardView>(R.id.cv3)
            (cv3.layoutParams as? ViewGroup.MarginLayoutParams)?.setMargins(12,12,12,12)
            cv3.requestLayout()
        }

        val cvItemColor = holder.itemView.findViewById<CardView>(R.id.cv1)
        cvItemColor.setOnClickListener {

            val manager = context.packageManager
            val editor = context.getSharedPreferences("nameAppIcon", AppCompatActivity.MODE_PRIVATE).edit()

            if (item.keyAppIcon == 1){
                editor.putInt("keyAppIcon", 1)
                editor.apply()

                manager.setComponentEnabledSetting(ComponentName(context, "com.multi_theme.Tools.ActivityMain"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)
                manager.setComponentEnabledSetting(ComponentName(context, "com.multi_theme.Tools.ActivityMainBlack"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)
                manager.setComponentEnabledSetting(ComponentName(context, "com.multi_theme.Tools.ActivityMainWight"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)

                Toast.makeText(context,"Green", Toast.LENGTH_SHORT).show()
            }
            if (item.keyAppIcon == 2){
                editor.putInt("keyAppIcon", 2)
                editor.apply()

                manager.setComponentEnabledSetting(ComponentName(context, "com.multi_theme.Tools.ActivityMain"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)
                manager.setComponentEnabledSetting(ComponentName(context, "com.multi_theme.Tools.ActivityMainBlack"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)
                manager.setComponentEnabledSetting(ComponentName(context, "com.multi_theme.Tools.ActivityMainWight"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)

                Toast.makeText(context, "Black", Toast.LENGTH_SHORT).show()
            }

            if (item.keyAppIcon == 3){
                editor.putInt("keyAppIcon", 3)
                editor.apply()

                manager.setComponentEnabledSetting(ComponentName(context, "com.multi_theme.Tools.ActivityMain"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)
                manager.setComponentEnabledSetting(ComponentName(context, "com.multi_theme.Tools.ActivityMainBlack"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)
                manager.setComponentEnabledSetting(ComponentName(context, "com.multi_theme.Tools.ActivityMainWight"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)

                Toast.makeText(context, "Wight", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return itemColor.size
    }
}