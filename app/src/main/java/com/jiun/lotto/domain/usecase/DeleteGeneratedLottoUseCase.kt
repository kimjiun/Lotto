package com.jiun.lotto.domain.usecase

import com.jiun.lotto.domain.common.Resource
import com.jiun.lotto.domain.model.GeneratedLotto
import com.jiun.lotto.domain.repository.LottoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteGeneratedLottoUseCase @Inject constructor(
    private val repository: LottoRepository
) {
    operator fun invoke(lotto: GeneratedLotto): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            repository.deleteGeneratedLotto(lotto)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
