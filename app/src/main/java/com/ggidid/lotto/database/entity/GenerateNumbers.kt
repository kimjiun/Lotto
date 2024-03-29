package com.ggidid.lotto.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenerateNumbers(
    @PrimaryKey
    val no: Int,
    val number1: Int,
    val number2: Int,
    val number3: Int,
    val number4: Int,
    val number5: Int,
    val number6: Int,
    val bonusNumber: Int,
)