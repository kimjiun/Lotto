package com.jiun.lotto.ui.feature_home

import com.jiun.lotto.domain.model.LottoData

data class HomeState(
    val isLoading: Boolean = false,
    val lottoData: LottoData? = null,
    val error: String? = null
)
