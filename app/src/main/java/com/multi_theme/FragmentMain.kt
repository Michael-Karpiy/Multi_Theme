package com.multi_theme

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.slider.Slider
import com.multi_theme.Adapter.AdapterAppIcon
import com.multi_theme.Adapter.AdapterCustomColor
import com.multi_theme.Adapter.AdapterStandardColor
import com.multi_theme.Item.ItemAppIcon
import com.multi_theme.Item.ItemStandardColor
import com.multi_theme.Tools.ActivityMain
import com.multi_theme.Tools.AppBarStateChangeListener
import com.multi_theme.Tools.CornerCardView
import com.multi_theme.Tools.DatabaseAddCustomColor
import com.multi_theme.Tools.FadeVisibility
import com.multi_theme.Tools.GravitySnapHelper
import com.multi_theme.Tools.ThemeActivity

class FragmentMain : Fragment() {

    private lateinit var cl: CoordinatorLayout
    private lateinit var container: ConstraintLayout

    private lateinit var cv0: CornerCardView
    private lateinit var tv0: TextView
    private lateinit var tvs0: TextView
    private lateinit var cvs0: CornerCardView
    private lateinit var tv0_1: TextView
    private lateinit var tv0_2: TextView

    private lateinit var cv1: CornerCardView
    private lateinit var tv1: TextView
    private lateinit var tvs1: TextView
    private lateinit var cvs1: CornerCardView
    private lateinit var tv1_1: TextView
    private lateinit var tv1_2: TextView

    private lateinit var cv2: CornerCardView
    private lateinit var tv2: TextView
    private lateinit var tvs2: TextView
    private lateinit var cvs2: CornerCardView
    private lateinit var tv2_1: TextView
    private lateinit var tv2_2: TextView

    private lateinit var v1: View
    private lateinit var tvStandardColor: TextView
    private lateinit var rvStandardColor: RecyclerView

    private lateinit var v2: View
    private lateinit var tvCustomColor: TextView
    private lateinit var rvCustomColor: RecyclerView

    private lateinit var v3: View
    private lateinit var darkenStandardColor: View
    private lateinit var darkenCustomColor: View
    private lateinit var tvCornerRadius: TextView

    private lateinit var cv3: CornerCardView
    private lateinit var tv3: TextView
    private lateinit var slider: Slider
    private lateinit var v4: View

    private lateinit var tvAppIcon: TextView
    private lateinit var rvAppIcon: RecyclerView
    private lateinit var v5: View

    private lateinit var abl: AppBarLayout
    private lateinit var clAppbar: ConstraintLayout
    private lateinit var tvTitle1: TextView
    private lateinit var tvTitle2: TextView
    private lateinit var cvPalette: CardView
    private lateinit var ivPalette: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        cl = requireView().findViewById(R.id.cl)
        container = requireView().findViewById(R.id.container)

        cv0 = requireView().findViewById(R.id.cv0)
        tv0 = requireView().findViewById(R.id.tv0)
        tvs0 = requireView().findViewById(R.id.tvs0)
        cvs0 = requireView().findViewById(R.id.cvs0)
        tv0_1 = requireView().findViewById(R.id.tv0_1)
        tv0_2 = requireView().findViewById(R.id.tv0_2)

        cv1 = requireView().findViewById(R.id.cv1)
        tv1 = requireView().findViewById(R.id.tv1)
        tvs1 = requireView().findViewById(R.id.tvs1)
        cvs1 = requireView().findViewById(R.id.cvs1)
        tv1_1 = requireView().findViewById(R.id.tv1_1)
        tv1_2 = requireView().findViewById(R.id.tv1_2)

        cv2 = requireView().findViewById(R.id.cv2)
        tv2 = requireView().findViewById(R.id.tv2)
        tvs2 = requireView().findViewById(R.id.tvs2)
        cvs2 = requireView().findViewById(R.id.cvs2)
        tv2_1 = requireView().findViewById(R.id.tv2_1)
        tv2_2 = requireView().findViewById(R.id.tv2_2)

        v1 = requireView().findViewById(R.id.v1)
        tvStandardColor = requireView().findViewById(R.id.tvStandardColor)
        rvStandardColor = requireView().findViewById(R.id.rvStandardColor)

        v2 = requireView().findViewById(R.id.v2)
        tvCustomColor = requireView().findViewById(R.id.tvCustomColor)
        rvCustomColor = requireView().findViewById(R.id.rvCustomColor)

        v3 = requireView().findViewById(R.id.v3)
        darkenStandardColor = requireView().findViewById(R.id.darkenStandardColor)
        darkenCustomColor = requireView().findViewById(R.id.darkenCustomColor)
        tvCornerRadius = requireView().findViewById(R.id.tvCornerRadius)

        cv3 = requireView().findViewById(R.id.cv3)
        tv3 = requireView().findViewById(R.id.tv3)
        slider = requireView().findViewById(R.id.slider)
        v4 = requireView().findViewById(R.id.v4)

        tvAppIcon = requireView().findViewById(R.id.tvAppIcon)
        rvAppIcon = requireView().findViewById(R.id.rvAppIcon)
        v5 = requireView().findViewById(R.id.v5)

        abl = requireView().findViewById(R.id.abl)
        clAppbar = requireView().findViewById(R.id.clAppbar)
        tvTitle1 = requireView().findViewById(R.id.tvTitle1)
        tvTitle2 = requireView().findViewById(R.id.tvTitle2)
        cvPalette = requireView().findViewById(R.id.cvPalette)
        ivPalette = requireView().findViewById(R.id.ivPalette)

        standartAndroidTheme()
        edgeToEdge()
        colorStatusBar()
        standardColor()
        customColor()
        cornerRadius()
        appIcon()
        applyingColor()
        palette()
        appBar()
    }

    private fun palette() {
        cvPalette.setOnClickListener {

            (context as ActivityMain).showFragment(FragmentPalette())
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

            cv0.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))
            tv0.setTextColor(Color.parseColor(ThemeActivity.colorTextMainLight))
            tvs0.setTextColor(Color.parseColor(ThemeActivity.colorTextSecondLight))
            cvs0.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))

            cv1.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))
            tv1.setTextColor(Color.parseColor(ThemeActivity.colorTextMainLight))
            tvs1.setTextColor(Color.parseColor(ThemeActivity.colorTextSecondLight))
            cvs1.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))

            cv2.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))
            tv2.setTextColor(Color.parseColor(ThemeActivity.colorTextMainLight))
            tvs2.setTextColor(Color.parseColor(ThemeActivity.colorTextSecondLight))
            cvs2.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))

            v1.setBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))
            tvStandardColor.setTextColor(Color.parseColor(ThemeActivity.colorAccentText))

            v2.setBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))
            tvCustomColor.setTextColor(Color.parseColor(ThemeActivity.colorAccentText))

            v3.setBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))
            tvCornerRadius.setTextColor(Color.parseColor(ThemeActivity.colorAccentText))

            cv3.setCardBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundSecondLight))
            slider.thumbTintList = ColorStateList.valueOf(Color.parseColor(ThemeActivity.colorBackgroundSecondLight))
            slider.tickActiveTintList = ColorStateList.valueOf(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))
            slider.tickInactiveTintList = ColorStateList.valueOf(Color.parseColor(ThemeActivity.colorBackgroundSecondLight))
            slider.trackActiveTintList = ColorStateList.valueOf(Color.parseColor(ThemeActivity.colorBackgroundSecondLight))
            slider.trackInactiveTintList = ColorStateList.valueOf(Color.parseColor(ThemeActivity.colorBackgroundSecondDark))
            v4.setBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))

            tvAppIcon.setTextColor(Color.parseColor(ThemeActivity.colorAccentText))
            v5.setBackgroundColor(Color.parseColor(ThemeActivity.colorBackgroundThirdDark))

            tvTitle1.setTextColor(Color.parseColor(ThemeActivity.colorTextMainLight))
            tvTitle2.setTextColor(Color.parseColor(ThemeActivity.colorTextMainLight))
            ivPalette.setColorFilter(Color.parseColor(ThemeActivity.colorIconMainLight))
            ImageViewCompat.setImageTintList(ivPalette, ColorStateList.valueOf(Color.parseColor(ThemeActivity.colorIconMainLight)))

            val gspStandardAndroidTheme = requireContext().getSharedPreferences(
                "nameStandardAndroidTheme", AppCompatActivity.MODE_PRIVATE
            ).getBoolean("keyStandardAndroidTheme", false)
            if (gspStandardAndroidTheme) {
                tv0_1.setTextColor(Color.parseColor(ThemeActivity.colorNoActiveLight))
                tv0_2.setTextColor(Color.parseColor(ThemeActivity.colorActiveLight))
            } else {
                tv0_1.setTextColor(Color.parseColor(ThemeActivity.colorActiveLight))
                tv0_2.setTextColor(Color.parseColor(ThemeActivity.colorNoActiveLight))
            }

            val gspEdgeToEdge = requireContext().getSharedPreferences(
                "nameEdgeToEdge", AppCompatActivity.MODE_PRIVATE
            ).getBoolean("keyEdgeToEdge", false)
            if (gspEdgeToEdge) {
                tv1_1.setTextColor(Color.parseColor(ThemeActivity.colorNoActiveLight))
                tv1_2.setTextColor(Color.parseColor(ThemeActivity.colorActiveLight))
            } else {
                tv1_1.setTextColor(Color.parseColor(ThemeActivity.colorActiveLight))
                tv1_2.setTextColor(Color.parseColor(ThemeActivity.colorNoActiveLight))
            }

            val gspColorStatusBar = requireContext().getSharedPreferences(
                "nameColorStatusBar", AppCompatActivity.MODE_PRIVATE
            ).getBoolean("keyColorStatusBar", false)
            if (gspColorStatusBar) {
                tv2_1.setTextColor(Color.parseColor(ThemeActivity.colorNoActiveLight))
                tv2_2.setTextColor(Color.parseColor(ThemeActivity.colorActiveLight))
            } else {
                tv2_1.setTextColor(Color.parseColor(ThemeActivity.colorActiveLight))
                tv2_2.setTextColor(Color.parseColor(ThemeActivity.colorNoActiveLight))
            }

        } else {

            val colorTypeValue = TypedValue()
            val themeColor = requireActivity().theme

            themeColor.resolveAttribute(R.attr.colorNoActiveLight, colorTypeValue, true)
            @ColorInt val ColorNoActiveLight = colorTypeValue.data

            themeColor.resolveAttribute(R.attr.colorActiveLight, colorTypeValue, true)
            @ColorInt val ColorActiveLight = colorTypeValue.data

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

            darkenStandardColor.background = ColorDrawable(Color.argb(128, 0, 0, 0))
            darkenCustomColor.background = ColorDrawable(Color.argb(128, 0, 0, 0))

            val gspStandardAndroidTheme = requireContext().getSharedPreferences("nameStandardAndroidTheme", AppCompatActivity.MODE_PRIVATE).getBoolean("keyStandardAndroidTheme", false)
            if (gspStandardAndroidTheme) {
                tv0_1.setTextColor(ColorNoActiveLight)
                tv0_2.setTextColor(ColorActiveLight)
            } else {
                tv0_1.setTextColor(ColorActiveLight)
                tv0_2.setTextColor(ColorNoActiveLight)
            }

            val gspEdgeToEdge = requireContext().getSharedPreferences(
                "nameEdgeToEdge", AppCompatActivity.MODE_PRIVATE
            ).getBoolean("keyEdgeToEdge", false)
            if (gspEdgeToEdge) {
                tv1_1.setTextColor(ColorNoActiveLight)
                tv1_2.setTextColor(ColorActiveLight)
            } else {
                tv1_1.setTextColor(ColorActiveLight)
                tv1_2.setTextColor(ColorNoActiveLight)
            }

            val gspColorStatusBar = requireContext().getSharedPreferences(
                "nameColorStatusBar", AppCompatActivity.MODE_PRIVATE
            ).getBoolean("keyColorStatusBar", false)
            if (gspColorStatusBar) {
                tv2_1.setTextColor(ColorNoActiveLight)
                tv2_2.setTextColor(ColorActiveLight)
            } else {
                tv2_1.setTextColor(ColorActiveLight)
                tv2_2.setTextColor(ColorNoActiveLight)
            }
        }
    }

    private fun createAppIcon(): List<ItemAppIcon> {

        val contacts = mutableListOf<ItemAppIcon>()

        contacts.add(ItemAppIcon(1))
        contacts.add(ItemAppIcon(2))
        contacts.add(ItemAppIcon(3))

        return contacts
    }

    private fun createStandardColor(): List<ItemStandardColor> {

        val contacts = mutableListOf<ItemStandardColor>()

        contacts.add(
            ItemStandardColor(
                0,

                "#F1F1F1", "#F1F1F1",

                "#FCFCFC", "#E2E2E2",

                "#303030", "#C6C6C6",

                "#F1F1F1", "#E2E2E2",

                "#E2E2E2", "#E2E2E2",

                "#F1F1F1", "#F1F1F1",

                "#474747", "#303030",

                "#5E5E5E", "#474747",

                "#ABABAB", "#C6C6C6",

                "#303030", "#C6C6C6",

                "#474747", "#303030",

                "#5E5E5E", "#474747",

                "#FFDAD6", "#410002", "#303030",

                "#303030", "#303030", "#303030"
            )
        )
        contacts.add(
            ItemStandardColor(
                1,

                "#1B1B1B", "#1B1B1B",

                "#E2E2E2", "#303030",

                "#C6C6C6", "#474747",

                "#1B1B1B", "#474747",

                "#474747", "#474747",

                "#1B1B1B", "#1B1B1B",

                "#E2E2E2", "#1B1B1B",

                "#C6C6C6", "#303030",

                "#767676", "#919191",

                "#C6C6C6", "#303030",

                "#E2E2E2", "#1B1B1B",

                "#C6C6C6", "#303030",

                "#410003", "#FFB3AD", "#FFB3AD",

                "#303030", "#C6C6C6", "#C6C6C6"
            )
        )
        contacts.add(
            ItemStandardColor(
                2,

                "#1B1D0F", "#1B1D0F",

                "#E4E4CE", "#303123",

                "#C8C8B3", "#474838",

                "#1B1D0F", "#474838",

                "#474838", "#474838",

                "#1B1D0F", "#1B1D0F",

                "#E4E4CE", "#1B1D0F",

                "#C8C8B3", "#303123",

                "#777765", "#92927F",

                "#C0D100", "#2E3300",

                "#E4E4CE", "#1B1D0F",

                "#C8C8B3", "#303123",

                "#410003", "#FFB3AD", "#FFB3AD",

                "#283412", "#BCCD9D", "#BCCD9D"
            )
        )
        return contacts
    }

    private fun appIcon() {
        val snapHelper = GravitySnapHelper(Gravity.START)
        val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val adapter = AdapterAppIcon(requireContext(), createAppIcon())
        rvAppIcon.adapter = adapter
        rvAppIcon.layoutManager = layoutManager
        rvAppIcon.setHasFixedSize(true)
        rvAppIcon.itemAnimator?.changeDuration = 0
        snapHelper.attachToRecyclerView(rvAppIcon)
    }

    private fun cornerRadius() {
        val gspCornerRadius = requireContext().getSharedPreferences(
            "nameCornerRadius", AppCompatActivity.MODE_PRIVATE
        )
        val keyCornerRadius = gspCornerRadius.getFloat("keyCornerRadius", 0.0f)

        slider.value = keyCornerRadius
        tv3.text = String.format("%.0f", keyCornerRadius)

        slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {}
            override fun onStopTrackingTouch(seekBar: Slider) {}
        })
        slider.addOnChangeListener { _, value, _ ->
            requireActivity().runOnUiThread {
                cv0.setCornerRadius(value)
                cv1.setCornerRadius(value)
                cv2.setCornerRadius(value)
                cv3.setCornerRadius(value)
                cvs0.setCornerRadius(value)
                cvs1.setCornerRadius(value)
                cvs2.setCornerRadius(value)

                tv3.text = String.format("%.0f", value)

                val eCornerRadius = requireContext().getSharedPreferences(
                    "nameCornerRadius", AppCompatActivity.MODE_PRIVATE
                ).edit()
                eCornerRadius.putFloat("keyCornerRadius", value)
                eCornerRadius.apply()
            }
        }
    }

    private fun customColor() {
        val databaseHelper = DatabaseAddCustomColor(context)
        val itemSearch = ArrayList(databaseHelper.notes)

        val snapHelper = GravitySnapHelper(Gravity.START)
        val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val adapterCustomColor = AdapterCustomColor(requireContext(), itemSearch)
        rvCustomColor.setHasFixedSize(true)
        rvCustomColor.layoutManager = layoutManager
        rvCustomColor.adapter = adapterCustomColor
        rvCustomColor.itemAnimator?.changeDuration = 0
        snapHelper.attachToRecyclerView(rvCustomColor)
    }

    private fun standardColor() {
        val snapHelper = GravitySnapHelper(Gravity.START)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val adapter = AdapterStandardColor(requireContext(), createStandardColor())
        rvStandardColor.adapter = adapter
        rvStandardColor.setHasFixedSize(true)
        rvStandardColor.layoutManager = layoutManager
        rvStandardColor.itemAnimator?.changeDuration = 0
        snapHelper.attachToRecyclerView(rvStandardColor)
    }

    private fun colorStatusBar() {
        val gspColorStatusBar = requireContext().getSharedPreferences("nameColorStatusBar", AppCompatActivity.MODE_PRIVATE)
        var keyColorStatusBar: Boolean = gspColorStatusBar.getBoolean("keyColorStatusBar", false)
        val eColorStatusBar = requireContext().getSharedPreferences("nameColorStatusBar", AppCompatActivity.MODE_PRIVATE).edit()

        val decorView: View = requireActivity().window.decorView
        val wic = WindowInsetsControllerCompat(requireActivity().window, decorView)

        if (keyColorStatusBar) {
            wic.isAppearanceLightStatusBars = true
        } else {
            wic.isAppearanceLightStatusBars = false
        }

        cv2.setOnClickListener {
            keyColorStatusBar = if (!keyColorStatusBar) {
                eColorStatusBar.putBoolean("keyColorStatusBar", true).apply()
                requireActivity().recreate()
                true
            } else {
                eColorStatusBar.putBoolean("keyColorStatusBar", false).apply()
                requireActivity().recreate()
                false
            }
        }
    }

    private fun edgeToEdge() {
        val gspEdgeToEdge =
            requireContext().getSharedPreferences("nameEdgeToEdge", AppCompatActivity.MODE_PRIVATE)
        var keyEdgeToEdge: Boolean = gspEdgeToEdge.getBoolean("keyEdgeToEdge", false)
        val eEdgeToEdge =
            requireContext().getSharedPreferences("nameEdgeToEdge", AppCompatActivity.MODE_PRIVATE)
                .edit()

        if (keyEdgeToEdge) {
            WindowCompat.setDecorFitsSystemWindows(requireActivity().window, true)
        } else {
            WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
            edgeToEdgeOn()
        }

        ViewCompat.setOnApplyWindowInsetsListener(container) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = insets.bottom)
            windowInsets
        }

        cv1.setOnClickListener {
            keyEdgeToEdge = if (!keyEdgeToEdge) {
                eEdgeToEdge.putBoolean("keyEdgeToEdge", true).apply()

                WindowCompat.setDecorFitsSystemWindows(requireActivity().window, true)

                requireActivity().recreate()
                true
            } else {
                eEdgeToEdge.putBoolean("keyEdgeToEdge", false).apply()

                edgeToEdgeOn()

                requireActivity().recreate()
                false
            }
        }
    }

    private fun edgeToEdgeOn() {
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        val colorAppBar = TypedValue()
        val themeColor4 = requireActivity().theme
        themeColor4.resolveAttribute(R.attr.colorAppBarMain, colorAppBar, true)
        @ColorInt val ColorAppBarMain = colorAppBar.data

        requireActivity().window.navigationBarColor = Color.TRANSPARENT
        requireActivity().window.statusBarColor = ColorAppBarMain

        ViewCompat.setOnApplyWindowInsetsListener(cl) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
            }

            windowInsets
        }

        ViewCompat.setOnApplyWindowInsetsListener(container) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = insets.bottom)

            windowInsets
        }
    }

    private fun standartAndroidTheme() {
        val gspStandardAndroidTheme = requireContext().getSharedPreferences("nameStandardAndroidTheme", AppCompatActivity.MODE_PRIVATE)
        val keyStandardAndroidTheme: Boolean = gspStandardAndroidTheme.getBoolean("keyStandardAndroidTheme", false)
        val eStandardAndroidTheme = requireContext().getSharedPreferences("nameStandardAndroidTheme", AppCompatActivity.MODE_PRIVATE).edit()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            cv0.setOnClickListener {
                Toast.makeText(context,"Not available on your device because your Android version is lower than 9", Toast.LENGTH_SHORT).show()
            }
            eStandardAndroidTheme.putBoolean("keyStandardAndroidTheme", true).apply()
        } else {
            cv0.setOnClickListener {
                if (!keyStandardAndroidTheme) {
                    eStandardAndroidTheme.putBoolean("keyStandardAndroidTheme", true).apply()
                    requireActivity().recreate()
                } else {
                    eStandardAndroidTheme.putBoolean("keyStandardAndroidTheme", false).apply()
                    requireActivity().recreate()
                }
            }
        }
    }

    private fun appBar() {
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