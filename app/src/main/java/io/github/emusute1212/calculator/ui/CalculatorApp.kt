package io.github.emusute1212.calculator.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.emusute1212.calculator.ui.main.CalculatorScreenHost

@Composable
fun CalculatorApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        CalculatorScreenHost()
    }
}