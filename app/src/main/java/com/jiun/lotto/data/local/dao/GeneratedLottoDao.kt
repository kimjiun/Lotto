package com.jiun.lotto.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jiun.lotto.data.local.entity.GeneratedLottoEntity

@Dao
interface GeneratedLottoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGeneratedLotto(lotto: GeneratedLottoEntity)

    @Query("SELECT * FROM generated_lotto ORDER BY id DESC")
    suspend fun getGeneratedLottos(): List<GeneratedLottoEntity>

    @Delete
    suspend fun deleteGeneratedLotto(lotto: GeneratedLottoEntity)
}
