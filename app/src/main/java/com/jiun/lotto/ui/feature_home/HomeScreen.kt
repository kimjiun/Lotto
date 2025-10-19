package com.jiun.lotto.ui.feature_home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jiun.lotto.ui.components.GeneratedLottoItem
import com.jiun.lotto.ui.components.LottoTicket

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        state.error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = "최근 당첨 결과", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(top = 16.dp, start = 16.dp))
            state.lottoData?.let {
                LottoTicket(lottoData = it)
            }
            Text(text = "최근 생성한 번호", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(top = 16.dp, start = 16.dp))
            state.recentGeneratedLotto?.let {
                GeneratedLottoItem(lotto = it)
            }
        }
    }
}
