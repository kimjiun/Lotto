package com.jiun.lotto.ui.feature_generation

import com.jiun.lotto.domain.model.GeneratedLotto

data class NumberGeneratorState(
    val isLoading: Boolean = false,
    val generatedLottos: List<GeneratedLotto> = emptyList(),
    val error: String? = null
)
