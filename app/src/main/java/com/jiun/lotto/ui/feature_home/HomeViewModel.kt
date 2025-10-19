package com.jiun.lotto.ui.feature_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiun.lotto.domain.common.Resource
import com.jiun.lotto.domain.usecase.GetGeneratedLottoUseCase
import com.jiun.lotto.domain.usecase.GetLatestLottoDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestLottoDataUseCase: GetLatestLottoDataUseCase,
    private val getGeneratedLottoUseCase: GetGeneratedLottoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        getLatestLotto()
        getRecentGeneratedLotto()
    }

    private fun getLatestLotto() {
        getLatestLottoDataUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(lottoData = result.data, isLoading = false)
                }
                is Resource.Error -> {
                    _state.value = HomeState(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getRecentGeneratedLotto() {
        getGeneratedLottoUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(recentGeneratedLotto = result.data?.firstOrNull(), isLoading = false)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}
