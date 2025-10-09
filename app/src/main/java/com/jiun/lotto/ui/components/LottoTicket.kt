package com.jiun.lotto.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jiun.lotto.domain.model.LottoData
import com.jiun.lotto.ui.theme.LottoTheme

@Composable
fun LottoTicket(lottoData: LottoData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${lottoData.round}회 당첨결과",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "(${lottoData.date})",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                lottoData.numbers.forEach { number ->
                    LottoBall(number = number)
                }
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Plus Icon",
                    modifier = Modifier.size(24.dp)
                )
                LottoBall(number = lottoData.bonusNumber)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LottoTicketPreview() {
    LottoTheme {
        val sampleData = LottoData(
            round = 1192,
            date = "2025.10.04",
            numbers = listOf(10, 16, 23, 36, 39, 40),
            bonusNumber = 11
        )
        LottoTicket(lottoData = sampleData)
    }
}
