package com.jiun.lotto.data.repository

import android.util.Log
import com.jiun.lotto.data.local.dao.LottoHistoryDao
import com.jiun.lotto.data.local.entity.LottoHistoryEntity
import com.jiun.lotto.data.remote.api.ApiService
import com.jiun.lotto.data.remote.dto.LottoResponse
import com.jiun.lotto.domain.model.LottoData
import com.jiun.lotto.domain.repository.LottoRepository
import javax.inject.Inject

class LottoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val lottoHistoryDao: LottoHistoryDao
) : LottoRepository {

    override suspend fun getLatestLotto(): LottoData? {
        val latestLocalHistory = lottoHistoryDao.getLatestLottoHistory()
        val nextRound = (latestLocalHistory?.round ?: 0) + 1

        return try {
            val response = apiService.getLottoNumber(drwNo = nextRound)

            if (response.isSuccess) {
                val newHistoryEntity = response.toEntity()
                lottoHistoryDao.insertLottoHistory(newHistoryEntity)
                newHistoryEntity.toDomain()
            } else {
                latestLocalHistory?.toDomain()
            }
        } catch (e: Exception) {
            latestLocalHistory?.toDomain()
        }
    }

    private fun LottoResponse.toEntity(): LottoHistoryEntity {
        return LottoHistoryEntity(
            round = this.drwNo,
            date = this.drwNoDate,
            num1 = this.drwtNo1,
            num2 = this.drwtNo2,
            num3 = this.drwtNo3,
            num4 = this.drwtNo4,
            num5 = this.drwtNo5,
            num6 = this.drwtNo6,
            bonusNum = this.bnusNo
        )
    }

    private fun LottoHistoryEntity.toDomain(): LottoData {
        return LottoData(
            round = this.round,
            date = this.date,
            numbers = listOf(this.num1, this.num2, this.num3, this.num4, this.num5, this.num6).sorted(),
            bonusNumber = this.bonusNum
        )
    }
}
