package com.jiun.lotto.data.remote.api

import com.jiun.lotto.data.remote.dto.LottoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("common.do")
    suspend fun getLottoNumber(
        @Query("method") method: String = "getLottoNumber",
        @Query("drwNo") drwNo: Int
    ): LottoResponse
}