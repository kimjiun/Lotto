package com.jiun.lotto.ui.feature_winning_number

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiun.lotto.domain.common.Resource
import com.jiun.lotto.domain.usecase.GetLatestWinningNumbersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class WinningNumberViewModel @Inject constructor(
    private val getLatestWinningNumbersUseCase: GetLatestWinningNumbersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WinningNumberState())
    val state: StateFlow<WinningNumberState> = _state.asStateFlow()

    init {
        getLatestLotto()
    }

    private fun getLatestLotto() {
        getLatestWinningNumbersUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = WinningNumberState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = WinningNumberState(lottoData = result.data)
                }
                is Resource.Error -> {
                    _state.value = WinningNumberState(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}
