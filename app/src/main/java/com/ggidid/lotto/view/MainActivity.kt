package com.ggidid.lotto.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ggidid.lotto.R
import com.ggidid.lotto.adapter.ViewPagerAdapter
import com.ggidid.lotto.database.AppDataBase
import com.ggidid.lotto.database.entity.GenerateNumbers
import com.ggidid.lotto.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val TAG = "JIUN/${javaClass.name}"
    val LOGO_IMAGE_URL = "https://www.dhlottery.co.kr/images/layout/logo-header.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDataBase.getInstance(this@MainActivity)

        val list = mutableListOf<GenerateNumbers>()
        list.add(GenerateNumbers(1, 2, 3, 4, 5,6 ,7, 8))

        GlobalScope.launch(Dispatchers.IO) {
            db?.generateNumberDao()?.insert(list)
        }

        binding.pager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager){ tab, position ->

            when(position){
                0 -> tab.text = getString(R.string.tab_title_home)
                1 -> tab.text = getString(R.string.tab_title_history)
                2 -> tab.text = getString(R.string.tab_title_generate)
            }
        }.attach()

        Glide.with(this)
            .load(LOGO_IMAGE_URL)
            .override(700, 500)  // 크기 조절
            .placeholder(R.drawable.ic_launcher_background) // 지정한 이미지를 먼저 출력
            .error(R.drawable.ic_launcher_foreground) // 에러 발생시 이미지 출력
            .into(binding.logo)
    }
}