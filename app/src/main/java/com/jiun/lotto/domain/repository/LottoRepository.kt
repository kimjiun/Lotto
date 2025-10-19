package com.jiun.lotto.domain.repository

import com.jiun.lotto.domain.model.GeneratedLotto
import com.jiun.lotto.domain.model.LottoData

interface LottoRepository {
    suspend fun getLatestLotto(): LottoData?
    suspend fun getLottoHistory(): List<LottoData>

    suspend fun saveGeneratedLotto(numbers: List<Int>)
    suspend fun getGeneratedLotto(): List<GeneratedLotto>
    suspend fun deleteGeneratedLotto(lotto: GeneratedLotto)
}