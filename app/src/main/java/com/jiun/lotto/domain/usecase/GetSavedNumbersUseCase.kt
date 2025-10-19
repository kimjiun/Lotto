package com.jiun.lotto.domain.usecase

import com.jiun.lotto.domain.common.Resource
import com.jiun.lotto.domain.model.LottoData
import com.jiun.lotto.domain.repository.LottoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSavedNumbersUseCase @Inject constructor(
    private val repository: LottoRepository
) {
    operator fun invoke(): Flow<Resource<List<LottoData>>> = flow {
        try {
            emit(Resource.Loading())
            val lottoHistory = repository.getLottoHistory()
            emit(Resource.Success(lottoHistory))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
