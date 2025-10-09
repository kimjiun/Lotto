package com.jiun.lotto.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lotto_history")
data class LottoHistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "round")
    val round: Int,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "num1")
    val num1: Int,

    @ColumnInfo(name = "num2")
    val num2: Int,

    @ColumnInfo(name = "num3")
    val num3: Int,

    @ColumnInfo(name = "num4")
    val num4: Int,

    @ColumnInfo(name = "num5")
    val num5: Int,

    @ColumnInfo(name = "num6")
    val num6: Int,

    @ColumnInfo(name = "bonus_num")
    val bonusNum: Int
)