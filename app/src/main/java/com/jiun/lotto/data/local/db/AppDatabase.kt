package com.jiun.lotto.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jiun.lotto.data.local.dao.LottoHistoryDao
import com.jiun.lotto.data.local.entity.LottoHistoryEntity

@Database(entities = [LottoHistoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lottoDao(): LottoHistoryDao
}