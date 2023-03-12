package com.ggidid.lotto.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ggidid.lotto.database.entity.GenerateNumbers
import com.ggidid.lotto.model.LottoInfo

@Dao
interface GenerateNumbersDao {
    @Query("SELECT * FROM GenerateNumbers")
    fun allSelect(): PagingSource<Int, GenerateNumbers>

    @Query("SELECT * FROM GenerateNumbers")
    fun select(): List<GenerateNumbers>

    @Insert
    fun insert(history: List<GenerateNumbers>)

    @Query("DELETE FROM GenerateNumbers")
    fun clearAll()
}