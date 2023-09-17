package com.ggidid.lotto.database

import android.content.Context
import androidx.room.*
import com.ggidid.lotto.database.dao.GenerateNumbersDao
import com.ggidid.lotto.database.dao.LottoInfoDao
import com.ggidid.lotto.database.dao.RemoteKeyDao
import com.ggidid.lotto.database.entity.GenerateNumbers
import com.ggidid.lotto.model.LottoInfo
import com.ggidid.lotto.model.RemoteKey

@Database(entities = [LottoInfo::class, RemoteKey::class, GenerateNumbers::class], version = 2,
    autoMigrations = [AutoMigration(from=1, to=2)])
abstract class AppDataBase: RoomDatabase() {
    abstract fun lottoInfoDao(): LottoInfoDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun generateNumberDao(): GenerateNumbersDao

    companion object {
        private var Instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return Instance ?: synchronized(AppDataBase::class) {
                val ins = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "lotto"
                ).build()

                Instance = ins
                ins
            }
        }

        fun deleteInstance() {
            Instance = null
        }
    }
}