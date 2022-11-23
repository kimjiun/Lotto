package com.ggidid.lotto.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ggidid.lotto.Constants
import com.ggidid.lotto.ViewPagerAdapter
import com.ggidid.lotto.databinding.ActivityMainBinding
import com.ggidid.lotto.retrofit.LottoFactory
import com.ggidid.lotto.retrofit.model.LottoBody
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    val TAG = "JIUN/MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager){ tab, position ->
            tab.text = "TAB ${position}"
        }.attach()

        val round = getLatestRound()
        testLottoAPI(round)
    }

    fun testLottoAPI(round: Int){
        val lottoService = LottoFactory().create()

        lottoService.getNumber(Constants.METHOD, round).enqueue(object : Callback<LottoBody> {
            override fun onResponse(call: Call<LottoBody>, response: Response<LottoBody>) {
                val result = response.body()
                Log.d(TAG,"RST : ${response.body()}")

                if(result?.returnValue == "fail"){
                    testLottoAPI(round - 1)
                }
                else {
                    Log.d(TAG, "SUCCESS")
                }

            }

            override fun onFailure(call: Call<LottoBody>, t: Throwable) {
                Log.d("JIUN","T : ${t}")
            }
        })
    }

    fun getLatestRound(): Int {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val cDate = Date()
        val sDate = dateFormat.parse("2002-12-07 23:59:59")

        val result:Long = ((cDate.time - sDate.time) / (86400 * 1000 * 7)) + 2

        return result.toInt()
    }
}