package com.jiun.lotto.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jiun.lotto.R
import com.jiun.lotto.data.local.dao.LottoHistoryDao
import com.jiun.lotto.data.local.db.AppDatabase
import com.jiun.lotto.data.local.entity.LottoHistoryEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        lottoHistoryDaoProvider: Provider<LottoHistoryDao>
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "lotto.db"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    prePopulateDatabase(context, lottoHistoryDaoProvider.get())
                }
            }
        }).build()
    }

    private suspend fun prePopulateDatabase(context: Context, lottoHistoryDao: LottoHistoryDao) {
        val inputStream = context.resources.openRawResource(R.raw.lotto_history)
        val reader = BufferedReader(InputStreamReader(inputStream))
        android.util.Log.d("DatabaseModule", "Starting pre-population...")
        try {
            val lottoHistoryList = reader.readLines().mapNotNull { line ->
                val tokens = line.split(",")
                if (tokens.size == 9) {
                    try {
                        LottoHistoryEntity(
                            round = tokens[0].toInt(),
                            date = tokens[1],
                            num1 = tokens[2].toInt(),
                            num2 = tokens[3].toInt(),
                            num3 = tokens[4].toInt(),
                            num4 = tokens[5].toInt(),
                            num5 = tokens[6].toInt(),
                            num6 = tokens[7].toInt(),
                            bonusNum = tokens[8].toInt()
                        )
                    } catch (e: NumberFormatException) {
                        android.util.Log.e("DatabaseModule", "Failed to parse line: $line", e)
                        null
                    }
                } else {
                    android.util.Log.w("DatabaseModule", "Skipping malformed line: $line")
                    null
                }
            }
            lottoHistoryDao.insertAllLottoHistory(lottoHistoryList)
            android.util.Log.d("DatabaseModule", "Pre-population finished. Inserted ${lottoHistoryList.size} records.")
        } catch (e: Exception) {
            android.util.Log.e("DatabaseModule", "Error during pre-population", e)
        }
    }


    @Provides
    fun provideLottoDao(appDatabase: AppDatabase): LottoHistoryDao {
        return appDatabase.lottoHistoryDao()
    }
}