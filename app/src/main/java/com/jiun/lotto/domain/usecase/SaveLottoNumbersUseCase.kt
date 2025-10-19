package com.jiun.lotto.domain.usecase

import com.jiun.lotto.domain.common.Resource
import com.jiun.lotto.domain.repository.LottoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveLottoNumbersUseCase @Inject constructor(
    private val repository: LottoRepository
) {
    operator fun invoke(numbers: List<Int>): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            repository.saveGeneratedLotto(numbers)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
