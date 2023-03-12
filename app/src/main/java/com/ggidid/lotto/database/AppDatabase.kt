package com.ggidid.lotto.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ggidid.lotto.database.dao.GenerateNumbersDao
import com.ggidid.lotto.database.dao.LottoInfoDao
import com.ggidid.lotto.database.dao.RemoteKeyDao
import com.ggidid.lotto.database.entity.GenerateNumbers
import com.ggidid.lotto.model.LottoInfo
import com.ggidid.lotto.model.RemoteKey

@Database(entities = [LottoInfo::class, RemoteKey::class, GenerateNumbers::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun lottoInfoDao(): LottoInfoDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun generateNumberDao(): GenerateNumbersDao

    companion object {
        private var Instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase? {
            if (Instance == null) {
                synchronized(AppDataBase::class) {
                    Instance = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        "lotto"
                    ).build()
                }
            }
            return Instance
        }

        fun deleteInstance() {
            Instance = null
        }
    }
}