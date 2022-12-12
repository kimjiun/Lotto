package com.ggidid.lotto.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.ggidid.lotto.Constants
import com.ggidid.lotto.R
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
    val LOGO_IMAGE_URL = "https://www.dhlottery.co.kr/images/layout/logo-header.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager){ tab, position ->
            tab.text = "TAB ${position}"
        }.attach()

        Glide.with(this)
            .load(LOGO_IMAGE_URL)
            .override(700, 500)  // 크기 조절
            .placeholder(R.drawable.ic_launcher_background) // 지정한 이미지를 먼저 출력
            .error(R.drawable.ic_launcher_foreground) // 에러 발생시 이미지 출력
            .into(binding.logo)
    }
}