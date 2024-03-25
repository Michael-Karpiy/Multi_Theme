package com.multi_theme.Tools

import android.os.Bundle
import androidx.core.view.WindowCompat
import com.backstackfragment.BackStack.BackStackActivity
import com.multi_theme.databinding.ActivityMainBinding
import com.multi_theme.FragmentMain
import com.multi_theme.R

class ActivityMain : BackStackActivity(R.id.container) {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFragment(FragmentMain())
    }
}