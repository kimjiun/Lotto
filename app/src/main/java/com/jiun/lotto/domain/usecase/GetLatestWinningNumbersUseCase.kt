package com.jiun.lotto.domain.usecase

import com.jiun.lotto.domain.common.Resource
import com.jiun.lotto.domain.model.LottoData
import com.jiun.lotto.domain.repository.LottoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLatestWinningNumbersUseCase @Inject constructor(
    private val repository: LottoRepository
) {
    operator fun invoke(): Flow<Resource<LottoData>> = flow {
        try {
            emit(Resource.Loading())
            val lottoData = repository.getLatestLotto()
            if (lottoData != null) {
                emit(Resource.Success(lottoData))
            } else {
                emit(Resource.Error("저장된 로또 데이터가 없습니다."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "HTTP 오류가 발생했습니다."))
        } catch (e: IOException) {
            emit(Resource.Error("서버에 연결할 수 없습니다. 인터넷 연결을 확인해주세요."))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "알 수 없는 오류가 발생했습니다."))
        }
    }
}
