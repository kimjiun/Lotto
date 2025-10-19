package com.jiun.lotto.ui.feature_generation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiun.lotto.domain.common.Resource
import com.jiun.lotto.domain.usecase.GenerateLottoNumbersUseCase
import com.jiun.lotto.domain.usecase.GetGeneratedLottoUseCase
import com.jiun.lotto.domain.usecase.SaveLottoNumbersUseCase
import com.jiun.lotto.domain.model.GeneratedLotto
import com.jiun.lotto.domain.usecase.DeleteGeneratedLottoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NumberGeneratorViewModel @Inject constructor(
    private val generateLottoNumbersUseCase: GenerateLottoNumbersUseCase,
    private val saveLottoNumbersUseCase: SaveLottoNumbersUseCase,
    private val getGeneratedLottoUseCase: GetGeneratedLottoUseCase,
    private val deleteGeneratedLottoUseCase: DeleteGeneratedLottoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NumberGeneratorState())
    val state: StateFlow<NumberGeneratorState> = _state.asStateFlow()

    init {
        getGeneratedLottos()
    }

    fun generateAndSaveLotto() {
        generateLottoNumbersUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    result.data?.let {
                        saveLottoNumbers(it)
                    }
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveLottoNumbers(numbers: List<Int>) {
        saveLottoNumbersUseCase(numbers).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    getGeneratedLottos()
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getGeneratedLottos() {
        getGeneratedLottoUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = NumberGeneratorState(generatedLottos = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = NumberGeneratorState(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteLotto(lotto: GeneratedLotto) {
        deleteGeneratedLottoUseCase(lotto).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    getGeneratedLottos()
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}
