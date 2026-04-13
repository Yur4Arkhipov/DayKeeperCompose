package com.example.daykeepercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.daykeepercompose.core.navigation.Navigation
import com.example.daykeepercompose.ui.theme.DayKeeperComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DayKeeperComposeTheme {
                Navigation()
            }
        }
    }
}