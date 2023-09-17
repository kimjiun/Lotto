package com.ggidid.lotto.retrofit

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ggidid.lotto.Constants
import com.ggidid.lotto.model.LottoInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class LottoRetrofitRepo (application: Application){
    val TAG = "JIUN/${javaClass.name}"
    var lottoInfoData: MutableLiveData<LottoInfo> = MutableLiveData()

    fun getLottoInfo(round: Int){
        val resp = LottoFactory().create().getNumber(Constants.METHOD, round).enqueue(object : Callback<LottoInfo> {
            override fun onResponse(call: Call<LottoInfo>, response: Response<LottoInfo>) {
                if(response.isSuccessful) {
                    val result = response.body()
                    //Log.d(TAG, "RST : ${response.body()}")

                    result?.let {
                        if (result.returnValue == "fail") {
                            getLottoInfo(round - 1)
                        } else {
                            Log.d(TAG, "SUCCESS")
                            lottoInfoData.postValue(result)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<LottoInfo>, t: Throwable) {
                Log.d("JIUN","T : ${t}")
            }
        })
    }

    fun getLottoInfoData() : LiveData<LottoInfo> {
        return this.lottoInfoData
    }
}