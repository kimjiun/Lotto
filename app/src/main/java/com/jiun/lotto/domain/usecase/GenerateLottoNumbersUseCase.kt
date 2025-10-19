package com.jiun.lotto.domain.usecase

import com.jiun.lotto.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenerateLottoNumbersUseCase @Inject constructor() {
    operator fun invoke(): Flow<Resource<List<Int>>> = flow {
        try {
            emit(Resource.Loading())
            val numbers = (1..45).shuffled().take(6).sorted()
            emit(Resource.Success(numbers))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
