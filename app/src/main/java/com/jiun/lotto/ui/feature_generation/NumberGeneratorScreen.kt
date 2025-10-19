package com.jiun.lotto.ui.feature_generation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jiun.lotto.ui.components.GeneratedLottoItem
import com.jiun.lotto.ui.components.ManualInputDialog

@Composable
fun NumberGeneratorScreen(
    viewModel: NumberGeneratorViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        ManualInputDialog(
            onDismiss = { showDialog = false },
            onSave = {
                viewModel.saveLottoNumbers(it)
                showDialog = false
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { viewModel.generateAndSaveLotto() }) {
                Text("랜덤 번호 생성")
            }
            Button(onClick = { showDialog = true }) {
                Text("직접 생성")
            }
        }

        Divider()

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        state.error?.let {
            Text(text = it)
        }

        LazyColumn {
            items(state.generatedLottos) {
                GeneratedLottoItem(lotto = it, onDelete = { viewModel.deleteLotto(it) })
            }
        }
    }
}

