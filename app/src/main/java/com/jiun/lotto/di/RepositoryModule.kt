package com.jiun.lotto.di

import com.jiun.lotto.data.repository.LottoRepositoryImpl
import com.jiun.lotto.domain.repository.LottoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLottoRepository(
        lottoRepositoryImpl: LottoRepositoryImpl
    ): LottoRepository
}
