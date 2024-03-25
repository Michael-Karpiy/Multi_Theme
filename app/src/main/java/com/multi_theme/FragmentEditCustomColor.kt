package com.multi_theme

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.multi_theme.Adapter.AdapterEditCustomColor
import com.multi_theme.Tools.AppBarStateChangeListener
import com.multi_theme.Tools.DatabaseAddCustomColor
import com.multi_theme.Tools.FadeVisibility
import com.multi_theme.Tools.ThemeActivity
import com.multi_theme.Item.ItemPalette

class FragmentEditCustomColor : DialogFragment() {

    private lateinit var cl: CoordinatorLayout
    private lateinit var abl: AppBarLayout

    private lateinit var clAppbar: ConstraintLayout
    private lateinit var ivBack: ImageView
    private lateinit var tvTitle1: TextView
    private lateinit var tvTitle2: TextView

    private lateinit var rvPalette: RecyclerView
    private lateinit var cvBack: CardView

    private lateinit var ivDelete: ImageView
    private lateinit var cvDelete: CardView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_palette, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        cl = requireView().findViewById(R.id.cl)
        abl = requireView().findViewById(R.id.abl)
        clAppbar = requireView().findViewById(R.id.clAppbar)
        ivBack = requireView().findViewById(R.id.ivBack)
        tvTitle1 = requireView().findViewById(R.id.tvTitle1)
        tvTitle2 = requireView().findViewById(R.id.tvTitle2)
        rvPalette = requireView().findViewById(R.id.rvPalette)
        cvBack = requireView().findViewById(R.id.cvBack)

        ivDelete = requireView().findViewById(R.id.ivDelete)
        cvDelete = requireView().findViewById(R.id.cvDelete)

        cvBack.setOnClickListener { requireActivity().onBackPressed()}

        cvDelete.setOnClickListener {
            val p = requireArguments().getString("position")

            Log.e("ddddddddddd", p.toString())

            val gspeMainColor = requireContext().getSharedPreferences("nameColor", AppCompatActivity.MODE_PRIVATE).edit()
            gspeMainColor.putInt("colorPosition", 1)
            gspeMainColor.apply()

            val position: Int = Integer.valueOf(p!!)
            val databaseAddCustomColor = DatabaseAddCustomColor(context)
            databaseAddCustomColor.delete(position)
            requireActivity().onBackPressed()
        }

        appBar()
        applyingColor()
        edgeToEdge()
        pallete()
    }

    private lateinit var adapter: AdapterEditCustomColor

    @SuppressLint("Range")
    private fun pallete() {

        val contacts = mutableListOf<ItemPalette>()

        val p = requireArguments().getString("position")
        val position: Int = Integer.valueOf(p!!)

        val databaseAddCustomColor = DatabaseAddCustomColor(context)
        val db = databaseAddCustomColor.writableDatabase
        @SuppressLint("Recycle") val c = db.rawQuery("SELECT * FROM CustomColor WHERE colorQuantity = '$p'" , null)
        c.moveToNext()
        contacts.add(ItemPalette("colorBackgroundMainLight", c.getString(c.getColumnIndexOrThrow("colorBackgroundMainLight"))))
        contacts.add(ItemPalette("colorBackgroundMainDark", c.getString(c.getColumnIndexOrThrow("colorBackgroundMainDark"))))

        contacts.add(ItemPalette("colorBackgroundSecondLight", c.getString(c.getColumnIndexOrThrow("colorBackgroundSecondLight"))))
        contacts.add(ItemPalette("colorBackgroundSecondDark", c.getString(c.getColumnIndexOrThrow("colorBackgroundSecondDark"))))

        contacts.add(ItemPalette("colorBackgroundThirdLight", c.getString(c.getColumnIndexOrThrow("colorBackgroundThirdLight"))))
        contacts.add(ItemPalette("colorBackgroundThirdDark", c.getString(c.getColumnIndexOrThrow("colorBackgroundThirdDark"))))

        contacts.add(ItemPalette("colorAppBarMain", c.getString(c.getColumnIndexOrThrow("colorAppBarMain"))))
        contacts.add(ItemPalette("colorAppBarSecond", c.getString(c.getColumnIndexOrThrow("colorAppBarSecond"))))

        contacts.add(ItemPalette("colorStatusBarMain", c.getString(c.getColumnIndexOrThrow("colorStatusBarMain"))))
        contacts.add(ItemPalette("colorStatusBarSecond", c.getString(c.getColumnIndexOrThrow("colorStatusBarSecond"))))

        contacts.add(ItemPalette("colorNavigationBarMain", c.getString(c.getColumnIndexOrThrow("colorNavigationBarMain"))))
        contacts.add(ItemPalette("colorNavigationBarSecond", c.getString(c.getColumnIndexOrThrow("colorNavigationBarSecond"))))

        contacts.add(ItemPalette("colorTextMainLight", c.getString(c.getColumnIndexOrThrow("colorTextMainLight"))))
        contacts.add(ItemPalette("colorTextMainDark", c.getString(c.getColumnIndexOrThrow("colorTextMainDark"))))

        contacts.add(ItemPalette("colorTextSecondLight", c.getString(c.getColumnIndexOrThrow("colorTextSecondLight"))))
        contacts.add(ItemPalette("colorTextSecondDark", c.getString(c.getColumnIndexOrThrow("colorTextSecondDark"))))

        contacts.add(ItemPalette("colorNoActiveLight", c.getString(c.getColumnIndexOrThrow("colorNoActiveLight"))))
        contacts.add(ItemPalette("colorNoActiveDark", c.getString(c.getColumnIndexOrThrow("colorNoActiveDark"))))

        contacts.add(ItemPalette("colorActiveLight", c.getString(c.getColumnIndexOrThrow("colorActiveLight"))))
        contacts.add(ItemPalette("colorActiveDark", c.getString(c.getColumnIndexOrThrow("colorActiveDark"))))

        contacts.add(ItemPalette("colorIconMainLight", c.getString(c.getColumnIndexOrThrow("colorIconMainLight"))))
        contacts.add(ItemPalette("colorIconMainDark", c.getString(c.getColumnIndexOrThrow("colorIconMainDark"))))

        contacts.add(ItemPalette("colorIconSecondLight", c.getString(c.getColumnIndexOrThrow("colorIconSecondLight"))))
        contacts.add(ItemPalette("colorIconSecondDark", c.getString(c.getColumnIndexOrThrow("colorIconSecondDark"))))

        contacts.add(ItemPalette("colorErrorBackground", c.getString(c.getColumnIndexOrThrow("colorErrorBackground"))))
        contacts.add(ItemPalette("colorErrorText", c.getString(c.getColumnIndexOrThrow("colorErrorText"))))
        contacts.add(ItemPalette("colorErrorIcon", c.getString(c.getColumnIndexOrThrow("colorErrorIcon"))))

        contacts.add(ItemPalette("colorAccentBackground", c.getString(c.getColumnIndexOrThrow("colorAccentBackground"))))
        contacts.add(ItemPalette("colorAccentText", c.getString(c.getColumnIndexOrThrow("colorAccentText"))))
        contacts.add(ItemPalette("colorAccentIcon", c.getString(c.getColumnIndexOrThrow("colorAccentIcon"))))

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = AdapterEditCustomColor(requireContext(), contacts, position)
        rvPalette.setHasFixedSize(true)
        rvPalette.layoutManager = layoutManager
        rvPalette.adapter = adapter

    }

    private fun edgeToEdge() {
        val nameEdgeToEdge = requireContext().getSharedPreferences("nameEdgeToEdge", AppCompatActivity.MODE_PRIVATE)
        val keyEdgeToEdge: Boolean = nameEdgeToEdge.getBoolean("keyEdgeToEdge", false)

        if (keyEdgeToEdge) {
            WindowCompat.setDecorFitsSystemWindows(requireActivity().window, true)
        } else {
            WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
            edgeToEdgeOn()
        }

        ViewCompat.setOnApplyWindowInsetsListener(rvPalette) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = insets.bottom)
            windowInsets
        }
    }

    private fun edgeToEdgeOn() {
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)

        requireActivity().window.navigationBarColor = Color.TRANSPARENT

        ViewCompat.setOnApplyWindowInsetsListener(cl) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
            }

            windowInsets
        }

        ViewCompat.setOnApplyWindowInsetsListener(rvPalette) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = insets.bottom)

            windowInsets
        }
    }

    private fun applyingColor() {

        val gspStandardAndroidTheme = requireContext().getSharedPreferences("nameStandardAndroidTheme", AppCompatActivity.MODE_PRIVATE)
        val statusSAT: Boolean = gspStandardAndroidTheme.getBoolean("keyStandardAndroidTheme", false)
        if (statusSAT) {

            abl.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
                override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                    when (state) {
                        State.COLLAPSED -> {
                            abl.setBackgroundColor(Color.parseColor(ThemeActivity.colorAppBarSecond))
                            clAppbar.setBackgroundColor(Color.parseColor(ThemeActivity.colorAppBarSecond))
                            requireActivity().window.statusBarColor = Color.parseColor(ThemeActivity.colorAppBarSecond)
                        }
                        State.EXPANDED -> {
                            abl.setBackgroundColor(Color.parseColor(ThemeActivity.colorAppBarMain))
                            clAppbar.setBackgroundColor(Color.parseColor(ThemeActivity.colorAppBarMain))
                            requireActivity().window.statusBarColor = Color.parseColor(ThemeActivity.colorAppBarMain)
                        }
                        State.IDLE -> {
                            abl.setBackgroundColor(Color.parseColor(ThemeActivity.colorAppBarMain))
                            clAppbar.setBackgroundColor(Color.parseColor(ThemeActivity.colorAppBarMain))
                            requireActivity().window.statusBarColor = Color.parseColor(ThemeActivity.colorAppBarMain)
                        }
                        else -> {}
                    }
                }
            })

            cl.setBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundMainLight))
            ivBack.setColorFilter(Color.parseColor(ThemeActivity.colorTextMainLight))
            ivDelete.setColorFilter(Color.parseColor(ThemeActivity.colorTextMainLight))
            tvTitle1.setTextColor(Color.parseColor(ThemeActivity.colorTextMainLight))
            tvTitle2.setTextColor(Color.parseColor(ThemeActivity.colorTextMainLight))

        }else {
            val colorTypeValue = TypedValue()
            val themeColor = requireActivity().theme

            themeColor.resolveAttribute(R.attr.colorAppBarMain, colorTypeValue, true)
            @ColorInt val ColorAppBarMain = colorTypeValue.data

            themeColor.resolveAttribute(R.attr.colorAppBarSecond, colorTypeValue, true)
            @ColorInt val ColorAppBarSecond = colorTypeValue.data


            abl.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
                override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                    when (state) {
                        State.COLLAPSED -> {
                            abl.setBackgroundColor(ColorAppBarSecond)
                            clAppbar.setBackgroundColor(ColorAppBarSecond)
                            requireActivity().window.statusBarColor = ColorAppBarSecond
                        }

                        State.EXPANDED -> {
                            abl.setBackgroundColor(ColorAppBarMain)
                            clAppbar.setBackgroundColor(ColorAppBarMain)
                            requireActivity().window.statusBarColor = ColorAppBarMain
                        }

                        State.IDLE -> {
                            abl.setBackgroundColor(ColorAppBarMain)
                            clAppbar.setBackgroundColor(ColorAppBarMain)
                            requireActivity().window.statusBarColor = ColorAppBarMain
                        }

                        else -> {}
                    }
                }
            })
        }
    }

    private fun appBar(){

        abl.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                when (state) {
                    State.COLLAPSED -> {
                        FadeVisibility.fadeVisibility(tvTitle2, View.VISIBLE, 200)
                    }
                    State.EXPANDED -> {
                        FadeVisibility.fadeVisibility(tvTitle2, View.GONE, 200)
                    }
                    State.IDLE -> {
                        FadeVisibility.fadeVisibility(tvTitle2, View.GONE, 200)
                    }
                    else -> {}
                }
            }
        })
    }
}