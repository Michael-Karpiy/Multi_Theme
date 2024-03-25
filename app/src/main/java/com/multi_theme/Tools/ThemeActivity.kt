package com.multi_theme.Tools

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.multi_theme.R

abstract class ThemeActivity : AppCompatActivity() {
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)

        val gspStandardAndroidTheme = applicationContext.getSharedPreferences("nameStandardAndroidTheme", MODE_PRIVATE)
        val keyStandardAndroidTheme: Boolean = gspStandardAndroidTheme.getBoolean("keyStandardAndroidTheme", false)

        loadThemeColors()
        checkNullTC()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            setTheme(R.style.Theme_Multi_Theme_Off)
        } else {
            if (keyStandardAndroidTheme) {
                setTheme(R.style.Theme_Multi_Theme_Off)
            } else {
                setTheme(R.style.Theme_Multi_Theme_On)
            }
        }
    }

    private fun loadThemeColors() {
        val preferences = getSharedPreferences("nameColor", MODE_PRIVATE)
        colorBackgroundMainLight = preferences.getString("colorBackgroundMainLight", "").toString()
        colorBackgroundMainDark = preferences.getString("colorBackgroundMainDark", "").toString()

        colorBackgroundSecondLight = preferences.getString("colorBackgroundSecondLight", "").toString()
        colorBackgroundSecondDark = preferences.getString("colorBackgroundSecondDark", "").toString()

        colorBackgroundThirdLight = preferences.getString("colorBackgroundThirdLight", "").toString()
        colorBackgroundThirdDark = preferences.getString("colorBackgroundThirdDark", "").toString()

        colorAppBarMain = preferences.getString("colorAppBarMain", "").toString()
        colorAppBarSecond = preferences.getString("colorAppBarSecond", "").toString()

        colorStatusBarMain = preferences.getString("colorStatusBarMain", "").toString()
        colorStatusBarSecond = preferences.getString("colorStatusBarSecond", "").toString()

        colorNavigationBarMain = preferences.getString("colorNavigationBarMain", "").toString()
        colorNavigationBarSecond = preferences.getString("colorNavigationBarSecond", "").toString()

        colorTextMainLight = preferences.getString("colorTextMainLight", "").toString()
        colorTextMainDark = preferences.getString("colorTextMainDark", "").toString()

        colorTextSecondLight = preferences.getString("colorTextSecondLight", "").toString()
        colorTextSecondDark = preferences.getString("colorTextSecondDark", "").toString()

        colorNoActiveLight = preferences.getString("colorNoActiveLight", "").toString()
        colorNoActiveDark = preferences.getString("colorNoActiveDark", "").toString()

        colorActiveLight = preferences.getString("colorActiveLight", "").toString()
        colorActiveDark = preferences.getString("colorActiveDark", "").toString()

        colorIconMainLight = preferences.getString("colorIconMainLight", "").toString()
        colorIconMainDark = preferences.getString("colorIconMainDark", "").toString()

        colorIconSecondLight = preferences.getString("colorIconSecondLight", "").toString()
        colorIconSecondDark = preferences.getString("colorIconSecondDark", "").toString()

        colorErrorBackground = preferences.getString("colorErrorBackground", "").toString()
        colorErrorText = preferences.getString("colorErrorText", "").toString()
        colorErrorIcon = preferences.getString("colorErrorIcon", "").toString()

        colorAccentBackground = preferences.getString("colorAccentBackground", "").toString()
        colorAccentText = preferences.getString("colorAccentText", "").toString()
        colorAccentIcon = preferences.getString("colorAccentIcon", "").toString()
    }

    private fun checkNullTC() {
        if (colorBackgroundMainLight == "")
        {
            val eMainColor = this.getSharedPreferences("nameColor", MODE_PRIVATE).edit()

            eMainColor.putString("colorBackgroundMainLight", "#F1F1F1")
            eMainColor.putString("colorBackgroundMainDark", "#F1F1F1")

            eMainColor.putString("colorBackgroundSecondLight", "#FCFCFC")
            eMainColor.putString("colorBackgroundSecondDark", "#E2E2E2")

            eMainColor.putString("colorBackgroundThirdLight", "#303030")
            eMainColor.putString("colorBackgroundThirdDark", "#C6C6C6")

            eMainColor.putString("colorAppBarMain", "#F1F1F1")
            eMainColor.putString("colorAppBarSecond", "#E2E2E2")

            eMainColor.putString("colorStatusBarMain", "#E2E2E2")
            eMainColor.putString("colorStatusBarSecond", "#E2E2E2")

            eMainColor.putString("colorNavigationBarMain", "#F1F1F1")
            eMainColor.putString("colorNavigationBarSecond", "#F1F1F1")

            eMainColor.putString("colorTextMainLight", "#474747")
            eMainColor.putString("colorTextMainDark", "#303030")

            eMainColor.putString("colorTextSecondLight", "#5E5E5E")
            eMainColor.putString("colorTextSecondDark", "#474747")

            eMainColor.putString("colorNoActiveLight", "#ABABAB")
            eMainColor.putString("colorNoActiveDark", "#C6C6C6")

            eMainColor.putString("colorActiveLight", "#303030")
            eMainColor.putString("colorActiveDark", "#C6C6C6")

            eMainColor.putString("colorIconMainLight", "#474747")
            eMainColor.putString("colorIconMainDark", "#303030")

            eMainColor.putString("colorIconSecondLight", "#5E5E5E")
            eMainColor.putString("colorIconSecondDark", "#474747")

            eMainColor.putString("colorErrorBackground", "#FFDAD6")
            eMainColor.putString("colorErrorText", "#410002")
            eMainColor.putString("colorErrorIcon", "#303030")

            eMainColor.putString("colorAccentBackground", "#303030")
            eMainColor.putString("colorAccentText", "#303030")
            eMainColor.putString("colorAccentIcon", "#303030")
            eMainColor.apply()
        }
    }

    companion object {
        var quantityStandardColor = 2

        var CORNER_RADIUS = 50f
        var mCornerRadius = 0f

        lateinit var colorBackgroundMainLight: String
        lateinit var colorBackgroundMainDark: String

        lateinit var colorBackgroundSecondLight: String
        lateinit var colorBackgroundSecondDark: String

        lateinit var colorBackgroundThirdLight: String
        lateinit var colorBackgroundThirdDark: String

        lateinit var colorAppBarMain: String
        lateinit var colorAppBarSecond: String

        lateinit var colorStatusBarMain: String
        lateinit var colorStatusBarSecond: String

        lateinit var colorNavigationBarMain: String
        lateinit var colorNavigationBarSecond: String

        lateinit var colorTextMainLight: String
        lateinit var colorTextMainDark: String

        lateinit var colorTextSecondLight: String
        lateinit var colorTextSecondDark: String

        lateinit var colorNoActiveLight: String
        lateinit var colorNoActiveDark: String

        lateinit var colorActiveLight: String
        lateinit var colorActiveDark: String

        lateinit var colorIconMainLight: String
        lateinit var colorIconMainDark: String

        lateinit var colorIconSecondLight: String
        lateinit var colorIconSecondDark: String

        lateinit var colorErrorBackground: String
        lateinit var colorErrorText: String
        lateinit var colorErrorIcon: String

        lateinit var colorAccentBackground: String
        lateinit var colorAccentText: String
        lateinit var colorAccentIcon: String
    }
}