package com.jiun.lotto.ui.feature_winning_number

import com.jiun.lotto.domain.model.LottoData

data class WinningNumberState(
    val isLoading: Boolean = false,
    val lottoData: LottoData? = null,
    val error: String? = null
)
