package com.jiun.lotto.domain.model

import java.io.Serializable

data class LottoData(
    val round: Int,
    val date: String,
    val numbers: List<Int>,
    val bonusNumber: Int
) : Serializable
