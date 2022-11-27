package com.ggidid.lotto

import java.text.SimpleDateFormat
import java.util.*

class CommonFunc {
    companion object {
        fun getLatestRound(): Int {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val cDate = Date()
            val sDate = dateFormat.parse("2002-12-07 23:59:59")

            val result:Long = ((cDate.time - sDate.time) / (86400 * 1000 * 7)) + 2

            return result.toInt()
        }
    }
}