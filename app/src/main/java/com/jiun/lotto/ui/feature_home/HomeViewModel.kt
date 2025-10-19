package com.jiun.lotto.ui.feature_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiun.lotto.domain.common.Resource
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
    private val getLatestLottoDataUseCase: GetLatestLottoDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        getLatestLotto()
    }

    private fun getLatestLotto() {
        getLatestLottoDataUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = HomeState(lottoData = result.data)
                }
                is Resource.Error -> {
                    _state.value = HomeState(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}
