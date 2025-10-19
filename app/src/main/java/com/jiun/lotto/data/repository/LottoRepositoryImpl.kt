package com.jiun.lotto.data.repository

import android.util.Log
import com.jiun.lotto.data.local.dao.GeneratedLottoDao
import com.jiun.lotto.data.local.dao.LottoHistoryDao
import com.jiun.lotto.data.local.entity.GeneratedLottoEntity
import com.jiun.lotto.data.local.entity.LottoHistoryEntity
import com.jiun.lotto.data.remote.api.ApiService
import com.jiun.lotto.data.remote.dto.LottoResponse
import com.jiun.lotto.domain.model.GeneratedLotto
import com.jiun.lotto.domain.model.LottoData
import com.jiun.lotto.domain.repository.LottoRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class LottoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val lottoHistoryDao: LottoHistoryDao,
    private val generatedLottoDao: GeneratedLottoDao
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

    override suspend fun getLottoHistory(): List<LottoData> {
        return lottoHistoryDao.getLottoHistory().map { it.toDomain() }
    }

    override suspend fun saveGeneratedLotto(numbers: List<Int>) {
        val entity = GeneratedLottoEntity(
            date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
            num1 = numbers[0],
            num2 = numbers[1],
            num3 = numbers[2],
            num4 = numbers[3],
            num5 = numbers[4],
            num6 = numbers[5]
        )
        generatedLottoDao.insertGeneratedLotto(entity)
    }

    override suspend fun getGeneratedLotto(): List<GeneratedLotto> {
        return generatedLottoDao.getGeneratedLottos().map { it.toDomain() }
    }

    override suspend fun deleteGeneratedLotto(lotto: GeneratedLotto) {
        generatedLottoDao.deleteGeneratedLotto(lotto.toEntity())
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

    private fun GeneratedLottoEntity.toDomain(): GeneratedLotto {
        return GeneratedLotto(
            id = this.id,
            numbers = listOf(this.num1, this.num2, this.num3, this.num4, this.num5, this.num6),
            date = this.date
        )
    }

    private fun GeneratedLotto.toEntity(): GeneratedLottoEntity {
        return GeneratedLottoEntity(
            id = this.id,
            date = this.date,
            num1 = this.numbers[0],
            num2 = this.numbers[1],
            num3 = this.numbers[2],
            num4 = this.numbers[3],
            num5 = this.numbers[4],
            num6 = this.numbers[5]
        )
    }
}
