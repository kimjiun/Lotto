package com.ggidid.lotto.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ggidid.lotto.Constants
import com.ggidid.lotto.ViewPagerAdapter
import com.ggidid.lotto.databinding.ActivityMainBinding
import com.ggidid.lotto.retrofit.LottoFactory
import com.ggidid.lotto.retrofit.model.LottoInfo
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    val TAG = "JIUN/${javaClass.name}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager){ tab, position ->
            tab.text = "TAB ${position}"
        }.attach()
    }
}