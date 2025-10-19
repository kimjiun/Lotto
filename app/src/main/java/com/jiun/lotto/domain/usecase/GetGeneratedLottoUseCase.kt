package com.jiun.lotto.domain.usecase

import com.jiun.lotto.domain.common.Resource
import com.jiun.lotto.domain.model.GeneratedLotto
import com.jiun.lotto.domain.repository.LottoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGeneratedLottoUseCase @Inject constructor(
    private val repository: LottoRepository
) {
    operator fun invoke(): Flow<Resource<List<GeneratedLotto>>> = flow {
        try {
            emit(Resource.Loading())
            val generatedLotto = repository.getGeneratedLotto()
            emit(Resource.Success(generatedLotto))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
