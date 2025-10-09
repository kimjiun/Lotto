package com.jiun.lotto.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jiun.lotto.data.local.entity.LottoHistoryEntity

@Dao
interface LottoHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLottoHistory(lottoHistory: LottoHistoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLottoHistory(lottoHistory: List<LottoHistoryEntity>)

    @Query("SELECT * FROM lotto_history ORDER BY round DESC")
    suspend fun getLottoHistory(): List<LottoHistoryEntity>
}