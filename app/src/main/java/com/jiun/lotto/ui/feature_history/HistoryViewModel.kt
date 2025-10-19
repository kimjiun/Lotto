package com.jiun.lotto.ui.feature_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiun.lotto.domain.common.Resource
import com.jiun.lotto.domain.usecase.GetSavedNumbersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getSavedNumbersUseCase: GetSavedNumbersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = _state.asStateFlow()

    init {
        getHistory()
    }

    private fun getHistory() {
        getSavedNumbersUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = HistoryState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = HistoryState(lottoHistory = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = HistoryState(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}
