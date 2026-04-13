package com.example.daykeepercompose.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskBottomSheet(
    onAddTask: (String, String, Int, Int, Int) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var startHour by remember { mutableStateOf("") }
    var startMinute by remember { mutableStateOf("") }
    var endHour by remember { mutableStateOf("") }
    var endMinute by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Добавление задачи",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                label = { Text("Название задачи") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = taskDescription,
                onValueChange = { taskDescription = it },
                label = { Text("Описание") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                maxLines = 3
            )

            Text(
                text = "Время начала",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = startHour,
                    onValueChange = { input ->
                        if (input.isEmpty()) {
                            startHour = ""
                        } else if (input.all { it.isDigit() } && input.length <= 2) {
                            val hour = input.toIntOrNull() ?: 0
                            if (hour <= 23) {
                                startHour = input
                            }
                        }
                    },
                    label = { Text("Час") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = startMinute,
                    onValueChange = { input ->
                        if (input.isEmpty()) {
                            startMinute = ""
                        } else if (input.all { it.isDigit() } && input.length <= 2) {
                            val minute = input.toIntOrNull() ?: 0
                            if (minute <= 59) {
                                startMinute = input
                            }
                        }
                    },
                    label = { Text("Минута") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            Text(
                text = "Время окончания",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = endHour,
                    onValueChange = { input ->
                        if (input.isEmpty()) {
                            endHour = ""
                        } else if (input.all { it.isDigit() } && input.length <= 2) {
                            val hour = input.toIntOrNull() ?: 0
                            if (hour <= 23) {
                                endHour = input
                            }
                        }
                    },
                    label = { Text("Час") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = endMinute,
                    onValueChange = { input ->
                        if (input.isEmpty()) {
                            endMinute = ""
                        } else if (input.all { it.isDigit() } && input.length <= 2) {
                            val minute = input.toIntOrNull() ?: 0
                            if (minute <= 59) {
                                endMinute = input
                            }
                        }
                    },
                    label = { Text("Минута") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Отмена")
                }

                val isFormValid = taskName.isNotEmpty() &&
                    startHour.isNotEmpty() &&
                    startMinute.isNotEmpty() &&
                    endHour.isNotEmpty() &&
                    endMinute.isNotEmpty()

                Button(
                    onClick = {
                        if (isFormValid) {
                            try {
                                val startH = startHour.toInt()
                                val startM = startMinute.toIntOrNull() ?: 0

                                val endH = endHour.toInt()
                                val endM = endMinute.toIntOrNull() ?: 0

                                val startTotal = startH * 60 + startM
                                val endTotal = endH * 60 + endM
                                val duration = endTotal - startTotal

                                if (duration > 0) {
                                    onAddTask(
                                        taskName,
                                        taskDescription,
                                        startH,
                                        startM,
                                        duration
                                    )
                                    onDismiss()
                                }
                            } catch (e: Exception) {
                                Log.d("AddTask", "Error: $e")
                            }
                        }
                    },
                    enabled = isFormValid,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Добавить")
                }
            }
        }
    }
}