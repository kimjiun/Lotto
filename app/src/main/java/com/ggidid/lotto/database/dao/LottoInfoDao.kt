package com.ggidid.lotto.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ggidid.lotto.model.LottoInfo

@Dao
interface LottoInfoDao {
    @Query("SELECT * FROM LottoInfo")
    fun allSelect(): PagingSource<Int, LottoInfo>

    @Query("SELECT * FROM LottoInfo")
    fun select(): List<LottoInfo>

    @Insert
    fun insert(lottoInfo: LottoInfo)

    @Insert
    fun insertAll(lottoInfo: List<LottoInfo>)

    @Query("DELETE FROM LottoInfo")
    fun clearAll()
}