package com.ggidid.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ggidid.lotto.retrofit.LottoFactory
import com.ggidid.lotto.retrofit.model.LottoBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lottoService = LottoFactory().create()

        lottoService.getNumber(Constants.METHOD, 100).enqueue(object : Callback<LottoBody> {
            override fun onResponse(call: Call<LottoBody>, response: Response<LottoBody>) {
                Log.d("JIUN","RST : ${response.body()}")
            }

            override fun onFailure(call: Call<LottoBody>, t: Throwable) {
                Log.d("JIUN","T : ${t}")
            }
        })
    }
}