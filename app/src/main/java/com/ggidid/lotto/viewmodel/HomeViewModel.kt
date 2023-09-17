package com.ggidid.lotto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ggidid.lotto.CommonFunc
import com.ggidid.lotto.retrofit.LottoRetrofitRepo
import com.ggidid.lotto.model.LottoInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val application: Application): AndroidViewModel(application) {
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