package com.jiun.lotto.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jiun.lotto.data.local.dao.GeneratedLottoDao
import com.jiun.lotto.data.local.dao.LottoHistoryDao
import com.jiun.lotto.data.local.entity.GeneratedLottoEntity
import com.jiun.lotto.data.local.entity.LottoHistoryEntity

@Database(entities = [LottoHistoryEntity::class, GeneratedLottoEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lottoHistoryDao(): LottoHistoryDao
    abstract fun generatedLottoDao(): GeneratedLottoDao
}