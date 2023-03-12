package com.ggidid.lotto.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data  class RemoteKey(
    @PrimaryKey
    val repoId:String,
    val prevKey:Int?,
    val nextKey:Int?
)