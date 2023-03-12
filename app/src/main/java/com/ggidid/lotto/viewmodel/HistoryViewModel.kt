package com.ggidid.lotto.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ggidid.lotto.CommonFunc
import com.ggidid.lotto.Constants
import com.ggidid.lotto.retrofit.LottoFactory
import com.ggidid.lotto.retrofit.LottoRetrofitRepo
import com.ggidid.lotto.model.LottoInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class HistoryViewModel(application: Application): AndroidViewModel(application) {
    val TAG = "JIUN/${javaClass.name}"

    var lottoRetrofitRepo: LottoRetrofitRepo
    var lottoInfoData: LiveData<LottoInfo>

    init {
        lottoRetrofitRepo = LottoRetrofitRepo(application)
        lottoInfoData = lottoRetrofitRepo.getLottoInfoData()
    }

    fun getLottoInfo(){
        lottoRetrofitRepo.getLottoInfo(CommonFunc.getLatestRound())
    }

    fun getLottoInfoLiveData() : LiveData<LottoInfo> {
        return this.lottoInfoData
    }
}