package com.ggidid.lotto.retrofit

import com.ggidid.lotto.retrofit.model.LottoInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LottoService {
    @GET("common.do")
    fun getNumber(
        @Query("method") method: String,
        @Query("drwNo") drwNo: Int): Call<LottoInfo>
}