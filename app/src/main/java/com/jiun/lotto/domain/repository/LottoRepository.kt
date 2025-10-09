package com.jiun.lotto.domain.repository

import com.jiun.lotto.domain.model.LottoData

interface LottoRepository {
    suspend fun getLatestLotto(): LottoData?
}