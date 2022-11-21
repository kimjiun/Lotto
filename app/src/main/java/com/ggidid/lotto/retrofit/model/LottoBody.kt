package com.ggidid.lotto.retrofit.model

import com.google.gson.annotations.SerializedName

data class LottoBody (
    @SerializedName("totSellamnt")
    val totSellamnt: String,

    @SerializedName("returnValue")
    val returnValue: String,

    @SerializedName("drwNoDate")
    val drwNoDate: String,

    @SerializedName("firstWinamnt")
    val firstWinamnt: String,

    @SerializedName("drwtNo6")
    val drwtNo6: String,

    @SerializedName("drwtNo4")
    val drwtNo4: String,

    @SerializedName("firstPrzwnerCo")
    val firstPrzwnerCo: String,

    @SerializedName("drwtNo5")
    val drwtNo5: String,

    @SerializedName("bnusNo")
    val bnusNo: String,

    @SerializedName("firstAccumamnt")
    val firstAccumamnt: String,

    @SerializedName("drwNo")
    val drwNo: String,

    @SerializedName("drwtNo2")
    val drwtNo2: String,

    @SerializedName("drwtNo3")
    val drwtNo3: String,

    @SerializedName("drwtNo1")
    val drwtNo1: String,
)