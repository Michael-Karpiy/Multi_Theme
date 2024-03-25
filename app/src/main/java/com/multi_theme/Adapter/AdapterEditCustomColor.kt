package com.multi_theme.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.RecyclerView
import com.multi_theme.DialogEditCustomColor
import com.multi_theme.Item.ItemPalette
import com.multi_theme.R
import com.multi_theme.Tools.ActivityMain
import com.multi_theme.Tools.ThemeActivity

class AdapterEditCustomColor(private val context: Context, private val itemColor: List<ItemPalette>, val p: Int?) :
    RecyclerView.Adapter<AdapterEditCustomColor.VH>() {

    private lateinit var colorBackgroundMain: String

    private lateinit var colorBackgroundSecondLight: String
    private lateinit var colorBackgroundSecondDark: String

    private lateinit var colorBackgroundThirdLight: String
    private lateinit var colorBackgroundThirdDark: String

    private lateinit var colorAppBar: String
    private lateinit var colorStatusBar: String
    private lateinit var colorNavigationBar: String

    private lateinit var colorTextMainLight: String
    private lateinit var colorTextSubLight: String

    private lateinit var colorTextMainDark: String
    private lateinit var colorTextSubDark: String

    private lateinit var colorNoActiveLight: String
    private lateinit var colorNoActiveDark: String

    private lateinit var colorItemLight: String
    private lateinit var colorItemDark: String
    private lateinit var colorItem: String
    private lateinit var colorDarken: String

    private lateinit var colorAccentTextMain: String
    private lateinit var colorAccentTextSecond: String
    private lateinit var colorAccentTextThird: String

    private lateinit var colorAccentTextNoActiveDark: String

    private lateinit var colorAccentBackgroundMain: String
    private lateinit var colorAccentBackgroundSecond: String
    private lateinit var colorAccentBackgroundThird: String
    private lateinit var colorAccentItem: String

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val cv0: CardView = view.findViewById(R.id.cv0)
        val cv1: CardView = view.findViewById(R.id.cv1)
        val cv2: CardView = view.findViewById(R.id.cv2)
        val tv1: TextView = view.findViewById(R.id.tv1)
        val tv2: TextView = view.findViewById(R.id.tv2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.item_pallete, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = itemColor[position]

        val gspStandardAndroidTheme = context.getSharedPreferences("nameStandardAndroidTheme", AppCompatActivity.MODE_PRIVATE)
        val statusSAT: Boolean = gspStandardAndroidTheme.getBoolean("keyStandardAndroidTheme", false)
        if (statusSAT) {

            holder.cv0.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))
            holder.cv2.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))
            holder.tv1.setTextColor(Color.parseColor(ThemeActivity.colorTextMainLight))
            holder.tv2.setTextColor(Color.parseColor(ThemeActivity.colorTextSecondLight))
        }

        try {
            holder.cv1.setCardBackgroundColor(Color.parseColor(item.colorId))
        } catch (e: IllegalArgumentException) {
            holder.cv1.setCardBackgroundColor(Color.TRANSPARENT)
        }

        holder.tv1.text = item.colorName
        holder.tv2.text = item.colorId

        if (item.colorName == "colorBackgroundMain") colorBackgroundMain = holder.tv2.text.toString()

        if (item.colorName == "colorBackgroundSecondLight") colorBackgroundSecondLight = holder.tv2.text.toString()
        if (item.colorName == "colorBackgroundSecondDark") colorBackgroundSecondDark = holder.tv2.text.toString()

        if (item.colorName == "colorBackgroundThirdLight") colorBackgroundThirdLight = holder.tv2.text.toString()
        if (item.colorName == "colorBackgroundThirdDark") colorBackgroundThirdDark = holder.tv2.text.toString()

        if (item.colorName == "colorAppBar") colorAppBar = holder.tv2.text.toString()
        if (item.colorName == "colorStatusBar") colorStatusBar = holder.tv2.text.toString()
        if (item.colorName == "colorNavigationBar") colorNavigationBar = holder.tv2.text.toString()

        if (item.colorName == "colorTextMainLight") colorTextMainLight = holder.tv2.text.toString()
        if (item.colorName == "colorTextSubLight") colorTextSubLight = holder.tv2.text.toString()

        if (item.colorName == "colorTextMainDark") colorTextMainDark = holder.tv2.text.toString()
        if (item.colorName == "colorTextSubDark") colorTextSubDark = holder.tv2.text.toString()

        if (item.colorName == "colorNoActiveLight") colorNoActiveLight = holder.tv2.text.toString()
        if (item.colorName == "colorNoActiveDark") colorNoActiveDark = holder.tv2.text.toString()

        if (item.colorName == "colorItemLight") colorItemLight = holder.tv2.text.toString()
        if (item.colorName == "colorItemDark") colorItemDark = holder.tv2.text.toString()
        if (item.colorName == "colorItem") colorItem = holder.tv2.text.toString()
        if (item.colorName == "colorDarken") colorDarken = holder.tv2.text.toString()

        if (item.colorName == "colorAccentTextMain") colorAccentTextMain = holder.tv2.text.toString()
        if (item.colorName == "colorAccentTextSecond") colorAccentTextSecond = holder.tv2.text.toString()
        if (item.colorName == "colorAccentTextThird") colorAccentTextThird = holder.tv2.text.toString()

        if (item.colorName == "colorAccentTextNoActiveDark") colorAccentTextNoActiveDark = holder.tv2.text.toString()

        if (item.colorName == "colorAccentBackgroundMain") colorAccentBackgroundMain = holder.tv2.text.toString()
        if (item.colorName == "colorAccentBackgroundSecond") colorAccentBackgroundSecond = holder.tv2.text.toString()
        if (item.colorName == "colorAccentBackgroundThird") colorAccentBackgroundThird = holder.tv2.text.toString()

        if (item.colorName == "colorAccentItem") colorAccentItem = holder.tv2.text.toString()

        holder.cv0.setOnClickListener {
            val dialogEditCustomColor: DialogEditCustomColor = DialogEditCustomColor.newInstance()
            val bundle = Bundle()
            bundle.putString("colorName", item.colorName)
            bundle.putString("colorId", holder.tv2.text as String)
            bundle.putInt("colorPosition", p!!)
            dialogEditCustomColor.arguments = bundle
            dialogEditCustomColor.show((context as ActivityMain).supportFragmentManager, "dialogEditCustomColor")

            if (item.colorName == "colorBackgroundMain") colorBackgroundMain = holder.tv2.text.toString()

            if (item.colorName == "colorBackgroundSecondLight") colorBackgroundSecondLight = holder.tv2.text.toString()
            if (item.colorName == "colorBackgroundSecondDark") colorBackgroundSecondDark = holder.tv2.text.toString()

            if (item.colorName == "colorBackgroundThirdLight") colorBackgroundThirdLight = holder.tv2.text.toString()
            if (item.colorName == "colorBackgroundThirdDark") colorBackgroundThirdDark = holder.tv2.text.toString()

            if (item.colorName == "colorAppBar") colorAppBar = holder.tv2.text.toString()
            if (item.colorName == "colorStatusBar") colorStatusBar = holder.tv2.text.toString()
            if (item.colorName == "colorNavigationBar") colorNavigationBar = holder.tv2.text.toString()

            if (item.colorName == "colorTextMainLight") colorTextMainLight = holder.tv2.text.toString()
            if (item.colorName == "colorTextSubLight") colorTextSubLight = holder.tv2.text.toString()

            if (item.colorName == "colorTextMainDark") colorTextMainDark = holder.tv2.text.toString()
            if (item.colorName == "colorTextSubDark") colorTextSubDark = holder.tv2.text.toString()

            if (item.colorName == "colorNoActiveLight") colorNoActiveLight = holder.tv2.text.toString()
            if (item.colorName == "colorNoActiveDark") colorNoActiveDark = holder.tv2.text.toString()

            if (item.colorName == "colorItemLight") colorItemLight = holder.tv2.text.toString()
            if (item.colorName == "colorItemDark") colorItemDark = holder.tv2.text.toString()
            if (item.colorName == "colorItem") colorItem = holder.tv2.text.toString()
            if (item.colorName == "colorDarken") colorDarken = holder.tv2.text.toString()

            if (item.colorName == "colorAccentTextMain") colorAccentTextMain = holder.tv2.text.toString()
            if (item.colorName == "colorAccentTextSecond") colorAccentTextSecond = holder.tv2.text.toString()
            if (item.colorName == "colorAccentTextThird") colorAccentTextThird = holder.tv2.text.toString()

            if (item.colorName == "colorAccentTextNoActiveDark") colorAccentTextNoActiveDark = holder.tv2.text.toString()

            if (item.colorName == "colorAccentBackgroundMain") colorAccentBackgroundMain = holder.tv2.text.toString()
            if (item.colorName == "colorAccentBackgroundSecond") colorAccentBackgroundSecond = holder.tv2.text.toString()
            if (item.colorName == "colorAccentBackgroundThird") colorAccentBackgroundThird = holder.tv2.text.toString()

            if (item.colorName == "colorAccentItem") colorAccentItem = holder.tv2.text.toString()

            dialogEditCustomColor.setFragmentResultListener("requestKey") { key, bundleResult ->
                if (key == "requestKey") {
                    holder.tv2.text = bundleResult.getString("et").toString()
                    if (item.colorName == "colorBackgroundMain") colorBackgroundMain = holder.tv2.text.toString()

                    if (item.colorName == "colorBackgroundSecondLight") colorBackgroundSecondLight = holder.tv2.text.toString()
                    if (item.colorName == "colorBackgroundSecondDark") colorBackgroundSecondDark = holder.tv2.text.toString()

                    if (item.colorName == "colorBackgroundThirdLight") colorBackgroundThirdLight = holder.tv2.text.toString()
                    if (item.colorName == "colorBackgroundThirdDark") colorBackgroundThirdDark = holder.tv2.text.toString()

                    if (item.colorName == "colorAppBar") colorAppBar = holder.tv2.text.toString()
                    if (item.colorName == "colorStatusBar") colorStatusBar = holder.tv2.text.toString()
                    if (item.colorName == "colorNavigationBar") colorNavigationBar = holder.tv2.text.toString()

                    if (item.colorName == "colorTextMainLight") colorTextMainLight = holder.tv2.text.toString()
                    if (item.colorName == "colorTextSubLight") colorTextSubLight = holder.tv2.text.toString()

                    if (item.colorName == "colorTextMainDark") colorTextMainDark = holder.tv2.text.toString()
                    if (item.colorName == "colorTextSubDark") colorTextSubDark = holder.tv2.text.toString()

                    if (item.colorName == "colorNoActiveLight") colorNoActiveLight = holder.tv2.text.toString()
                    if (item.colorName == "colorNoActiveDark") colorNoActiveDark = holder.tv2.text.toString()

                    if (item.colorName == "colorItemLight") colorItemLight = holder.tv2.text.toString()
                    if (item.colorName == "colorItemDark") colorItemDark = holder.tv2.text.toString()
                    if (item.colorName == "colorItem") colorItem = holder.tv2.text.toString()
                    if (item.colorName == "colorDarken") colorDarken = holder.tv2.text.toString()

                    if (item.colorName == "colorAccentTextMain") colorAccentTextMain = holder.tv2.text.toString()
                    if (item.colorName == "colorAccentTextSecond") colorAccentTextSecond = holder.tv2.text.toString()
                    if (item.colorName == "colorAccentTextThird") colorAccentTextThird = holder.tv2.text.toString()

                    if (item.colorName == "colorAccentTextNoActiveDark") colorAccentTextNoActiveDark = holder.tv2.text.toString()

                    if (item.colorName == "colorAccentBackgroundMain") colorAccentBackgroundMain = holder.tv2.text.toString()
                    if (item.colorName == "colorAccentBackgroundSecond") colorAccentBackgroundSecond = holder.tv2.text.toString()
                    if (item.colorName == "colorAccentBackgroundThird") colorAccentBackgroundThird = holder.tv2.text.toString()

                    if (item.colorName == "colorAccentItem") colorAccentItem = holder.tv2.text.toString()
                }
            }
        }
    }

    override fun getItemCount(): Int = itemColor.size
}