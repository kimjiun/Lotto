package com.jiun.lotto.ui.feature_history

import com.jiun.lotto.domain.model.LottoData

data class HistoryState(
    val isLoading: Boolean = false,
    val lottoHistory: List<LottoData> = emptyList(),
    val error: String? = null
)
