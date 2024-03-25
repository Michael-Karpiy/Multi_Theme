package com.multi_theme

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
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
import com.multi_theme.Adapter.AdapterPalette
import com.multi_theme.Item.ItemPalette
import com.multi_theme.Tools.AppBarStateChangeListener
import com.multi_theme.Tools.FadeVisibility
import com.multi_theme.Tools.ThemeActivity

class FragmentPalette : DialogFragment() {

    private lateinit var cl: CoordinatorLayout
    private lateinit var abl: AppBarLayout

    private lateinit var clAppbar: ConstraintLayout
    private lateinit var ivBack: ImageView
    private lateinit var tvTitle1: TextView
    private lateinit var tvTitle2: TextView

    private lateinit var rvPalette: RecyclerView
    private lateinit var cvBack: CardView

    private lateinit var cvDelete: CardView
    private lateinit var ivDelete: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_palette, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        cl = requireView().findViewById(R.id.cl)
        abl = requireView().findViewById(R.id.abl)
        clAppbar = requireView().findViewById(R.id.clAppbar)
        ivBack = requireView().findViewById(R.id.ivBack)
        ivDelete = requireView().findViewById(R.id.ivDelete)
        tvTitle1 = requireView().findViewById(R.id.tvTitle1)
        tvTitle2 = requireView().findViewById(R.id.tvTitle2)
        rvPalette = requireView().findViewById(R.id.rvPalette)
        cvBack = requireView().findViewById(R.id.cvBack)
        cvDelete = requireView().findViewById(R.id.cvDelete)

        cvBack.setOnClickListener { requireActivity().onBackPressed() }
        cvDelete.visibility = View.GONE

        appBar()
        applyingColor()
        edgeToEdge()
        pallete()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun pallete() {
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val adapter = AdapterPalette(requireContext(), createPalette())
        rvPalette.adapter = adapter
        rvPalette.layoutManager = layoutManager
        rvPalette.setHasFixedSize(true)
        rvPalette.itemAnimator?.changeDuration = 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createPalette(): List<ItemPalette> {

        val contacts = mutableListOf<ItemPalette>()

        val gspStandardAndroidTheme = requireContext().getSharedPreferences("nameStandardAndroidTheme", AppCompatActivity.MODE_PRIVATE)
        val keyStandardAndroidTheme: Boolean = gspStandardAndroidTheme.getBoolean("keyStandardAndroidTheme", false)

        if (keyStandardAndroidTheme) {
            contacts.add(ItemPalette("colorBackgroundMainLight", ThemeActivity.colorBackgroundMainLight))
            contacts.add(ItemPalette("colorBackgroundMainDark", ThemeActivity.colorBackgroundMainDark))

            contacts.add(ItemPalette("colorBackgroundSecondLight", ThemeActivity.colorBackgroundSecondLight))
            contacts.add(ItemPalette("colorBackgroundSecondDark", ThemeActivity.colorBackgroundSecondDark))

            contacts.add(ItemPalette("colorBackgroundThirdLight", ThemeActivity.colorBackgroundThirdLight))
            contacts.add(ItemPalette("colorBackgroundThirdDark", ThemeActivity.colorBackgroundThirdDark))

            contacts.add(ItemPalette("colorAppBarMain", ThemeActivity.colorAppBarMain))
            contacts.add(ItemPalette("colorAppBarSecond", ThemeActivity.colorAppBarSecond))

            contacts.add(ItemPalette("colorStatusBarMain", ThemeActivity.colorStatusBarMain))
            contacts.add(ItemPalette("colorStatusBarSecond", ThemeActivity.colorStatusBarSecond))

            contacts.add(ItemPalette("colorNavigationBarMain", ThemeActivity.colorNavigationBarMain))
            contacts.add(ItemPalette("colorNavigationBarSecond", ThemeActivity.colorNavigationBarSecond))

            contacts.add(ItemPalette("colorTextMainLight", ThemeActivity.colorTextMainLight))
            contacts.add(ItemPalette("colorTextMainDark", ThemeActivity.colorTextMainDark))

            contacts.add(ItemPalette("colorTextSecondLight", ThemeActivity.colorTextSecondLight))
            contacts.add(ItemPalette("colorTextSecondDark", ThemeActivity.colorTextSecondDark))

            contacts.add(ItemPalette("colorNoActiveLight", ThemeActivity.colorNoActiveLight))
            contacts.add(ItemPalette("colorNoActiveDark", ThemeActivity.colorNoActiveDark))

            contacts.add(ItemPalette("colorActiveLight", ThemeActivity.colorActiveLight))
            contacts.add(ItemPalette("colorActiveDark", ThemeActivity.colorActiveDark))

            contacts.add(ItemPalette("colorIconMainLight", ThemeActivity.colorIconMainLight))
            contacts.add(ItemPalette("colorIconMainDark", ThemeActivity.colorIconMainDark))

            contacts.add(ItemPalette("colorIconSecondLight", ThemeActivity.colorIconSecondLight))
            contacts.add(ItemPalette("colorIconSecondDark", ThemeActivity.colorIconSecondDark))

            contacts.add(ItemPalette("colorErrorBackground", ThemeActivity.colorErrorBackground))
            contacts.add(ItemPalette("colorErrorText", ThemeActivity.colorErrorText))
            contacts.add(ItemPalette("colorErrorIcon", ThemeActivity.colorErrorIcon))

            contacts.add(ItemPalette("colorAccentBackground", ThemeActivity.colorAccentBackground))
            contacts.add(ItemPalette("colorAccentText", ThemeActivity.colorAccentText))
            contacts.add(ItemPalette("colorAccentIcon", ThemeActivity.colorAccentIcon))
        } else {
           contacts.add(ItemPalette("colorBackgroundMainLight", getColorFromAttr(R.attr.colorBackgroundMainLight)))
           contacts.add(ItemPalette("colorBackgroundMainDark", getColorFromAttr(R.attr.colorBackgroundMainDark)))

           contacts.add(ItemPalette("colorBackgroundSecondLight", getColorFromAttr(R.attr.colorBackgroundSecondLight)))
           contacts.add(ItemPalette("colorBackgroundSecondDark", getColorFromAttr(R.attr.colorBackgroundSecondDark)))

           contacts.add(ItemPalette("colorBackgroundThirdLight", getColorFromAttr(R.attr.colorBackgroundThirdLight)))
           contacts.add(ItemPalette("colorBackgroundThirdDark", getColorFromAttr(R.attr.colorBackgroundThirdDark)))

           contacts.add(ItemPalette("colorAppBarMain", getColorFromAttr(R.attr.colorAppBarMain)))
           contacts.add(ItemPalette("colorAppBarSecond", getColorFromAttr(R.attr.colorAppBarSecond)))

           contacts.add(ItemPalette("colorStatusBarMain", getColorFromAttr(R.attr.colorStatusBarMain)))
           contacts.add(ItemPalette("colorStatusBarSecond", getColorFromAttr(R.attr.colorStatusBarSecond)))

           contacts.add(ItemPalette("colorNavigationBarMain", getColorFromAttr(R.attr.colorNavigationBarMain)))
           contacts.add(ItemPalette("colorNavigationBarSecond", getColorFromAttr(R.attr.colorNavigationBarSecond)))

           contacts.add(ItemPalette("colorTextMainLight", getColorFromAttr(R.attr.colorTextMainLight)))
           contacts.add(ItemPalette("colorTextMainDark", getColorFromAttr(R.attr.colorTextMainDark)))

           contacts.add(ItemPalette("colorTextSecondLight", getColorFromAttr(R.attr.colorTextSecondLight)))
           contacts.add(ItemPalette("colorTextSecondDark", getColorFromAttr(R.attr.colorTextSecondDark)))

           contacts.add(ItemPalette("colorNoActiveLight", getColorFromAttr(R.attr.colorNoActiveLight)))
           contacts.add(ItemPalette("colorNoActiveDark", getColorFromAttr(R.attr.colorNoActiveDark)))

           contacts.add(ItemPalette("colorActiveLight", getColorFromAttr(R.attr.colorActiveLight)))
           contacts.add(ItemPalette("colorActiveDark", getColorFromAttr(R.attr.colorActiveDark)))

           contacts.add(ItemPalette("colorIconMainLight", getColorFromAttr(R.attr.colorIconMainLight)))
           contacts.add(ItemPalette("colorIconMainDark", getColorFromAttr(R.attr.colorIconMainDark)))

           contacts.add(ItemPalette("colorIconSecondLight", getColorFromAttr(R.attr.colorIconSecondLight)))
           contacts.add(ItemPalette("colorIconSecondDark", getColorFromAttr(R.attr.colorIconSecondDark)))

           contacts.add(ItemPalette("colorErrorBackground", getColorFromAttr(R.attr.colorErrorBackground)))
           contacts.add(ItemPalette("colorErrorText", getColorFromAttr(R.attr.colorErrorText)))
           contacts.add(ItemPalette("colorErrorIcon", getColorFromAttr(R.attr.colorErrorIcon)))

           contacts.add(ItemPalette("colorAccentBackground", getColorFromAttr(R.attr.colorAccentBackground)))
           contacts.add(ItemPalette("colorAccentText", getColorFromAttr(R.attr.colorAccentText)))
           contacts.add(ItemPalette("colorAccentIcon", getColorFromAttr(R.attr.colorAccentIcon)))
        }

        return contacts
    }

    private fun getColorFromAttr(@AttrRes attrColor: Int): String {
        val typedArray = requireContext().theme.obtainStyledAttributes(intArrayOf(attrColor))
        val textColor = typedArray.getColor(0, 0)
        typedArray.recycle()

        return String.format("#%06X", 0xFFFFFF and textColor)
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
            requireActivity().window.statusBarColor = Color.parseColor(ThemeActivity.colorAppBarSecond)

        } else {
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