package com.jiun.lotto.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LottoResponse(
    val drwNo: Int, // 로또회차
    val drwNoDate: String, // 추첨일
    val drwtNo1: Int, // 당첨번호 1
    val drwtNo2: Int, // 당첨번호 2
    val drwtNo3: Int, // 당첨번호 3
    val drwtNo4: Int, // 당첨번호 4
    val drwtNo5: Int, // 당첨번호 5
    val drwtNo6: Int, // 당첨번호 6
    val totSellamnt: Long, // 총판매금액
    val returnValue: String, // 요청 결과 (success, fail)
    val firstWinamnt: Long, // 1등 당첨금액
    val firstPrzwnerCo: Int, // 1등 당첨인원
    val bnusNo: Int, // 보너스 번호
    val firstAccumamnt: Long, // 1등 당첨금 총액
)
