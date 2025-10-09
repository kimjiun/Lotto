package com.jiun.lotto

import android.app.Application
import com.jiun.lotto.data.local.db.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class LottoApplication : Application() {

    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        // Force database initialization by using it
        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
            appDatabase.lottoDao().getLottoHistory()
        }
    }
}