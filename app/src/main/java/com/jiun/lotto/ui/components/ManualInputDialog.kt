package com.jiun.lotto.ui.components

import android.R.attr.maxLines
import android.R.attr.singleLine
import android.R.attr.textStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ManualInputDialog(
    onDismiss: () -> Unit,
    onSave: (List<Int>) -> Unit
) {
    var numbers by remember { mutableStateOf(List(6) { "" }) }
    val isSaveEnabled = numbers.all { it.isNotEmpty() && it.toIntOrNull() in 1..45 } && numbers.toSet().size == 6

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("번호 직접 입력") },
        text = {
            Column {
                Text("1부터 45까지의 숫자 6개를 입력하세요.")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    for (i in 0..5) {
                        Box(modifier = Modifier
                            .weight(1f)
                            .background(color = Color.LightGray)
                            .padding(horizontal = 5.dp, vertical = 10.dp)
                        ){
                            BasicTextField(
                                modifier = Modifier.wrapContentSize(),
                                value = numbers[i],
                                onValueChange = { newValue ->
                                    if (newValue.length <= 2) {
                                        val newNumbers = numbers.toMutableList()
                                        newNumbers[i] = newValue
                                        numbers = newNumbers
                                    }
                                },
                                textStyle = TextStyle(fontSize = 14.sp),
                                singleLine = true,
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            )
                        }


                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onSave(numbers.map { it.toInt() }) },
                enabled = isSaveEnabled
            ) {
                Text("저장")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("취소")
            }
        }
    )
}
