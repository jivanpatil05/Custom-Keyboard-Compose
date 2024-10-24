package com.example.customkeyboardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.customkeyboardcompose.ui.theme.CustomKeyboardComposeTheme
import com.example.customkeyboardcompose.ui.theme.backButtonBackgroundColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomKeyboardComposeTheme {
                var weight by remember { mutableStateOf("") }
                val keyboardController = LocalSoftwareKeyboardController.current
                var isFieldFocused by remember { mutableStateOf(false) }
                var enable by remember { mutableStateOf(true) }
                var selectedItem by remember { mutableStateOf<String?>(null) }
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backButtonBackgroundColor)
                        .padding(5.dp),
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        CustomKeybordTextField(
                            weight = weight,
                            onValueChange = { newWeight ->
                                weight = newWeight },
                            isFocused = {
                                keyboardController?.hide()
                                isFieldFocused = it },
                            enable = enable,
                            unit = selectedItem ?: "Kg")
                        Spacer(modifier = Modifier.height(21.dp))
                        if (isFieldFocused) {
                            enable = false
                            CustomKeyBoard(
                                onclick = { weight = weight.plus(it) },
                                onDeleteIconClick = { weight = weight.dropLast(1) },
                                onDoneIconClick = {
                                    enable = false
                                    isFieldFocused = false
                                    if (isFieldFocused == false) {
                                        enable = true
                                    }
                                }, onItemSelected = { item ->
                                    selectedItem = item
                                })
                        }
                    }
                }
            }
        }
    }
}

