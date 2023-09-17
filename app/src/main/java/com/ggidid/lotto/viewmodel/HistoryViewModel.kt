package com.ggidid.lotto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ggidid.lotto.database.AppDataBase
import com.ggidid.lotto.retrofit.LottoFactory

class HistoryViewModel(application: Application): AndroidViewModel(application) {
    val TAG = "JIUN/${javaClass.name}"
    val db = AppDataBase.getInstance(application)
    val apiService = LottoFactory().create()

//    @ExperimentalPagingApi
//    fun getAllLottoInfo() : Flow<PagingData<LottoInfo>> {
//        db?.let {
//            return Pager(
//                config = PagingConfig(100, enablePlaceholders = false),
//                pagingSourceFactory = { db.lottoInfoDao().allSelect() },
//                remoteMediator = LottoRemoteMediator(db, apiService)
//            ).flow.cachedIn(viewModelScope)
//        }
//
//        return flowOf()
//    }
}