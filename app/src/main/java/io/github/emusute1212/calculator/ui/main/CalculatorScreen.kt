package io.github.emusute1212.calculator.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.emusute1212.calculator.model.entity.CalcEntity
import io.github.emusute1212.calculator.model.entity.Controller
import io.github.emusute1212.calculator.ui.component.AutoSizeText
import io.github.emusute1212.calculator.ui.component.AutoTextAlign
import io.github.emusute1212.calculator.ui.main.section.numbers.NumberButtonSection
import io.github.emusute1212.calculator.ui.main.section.numbers.NumberButtonSectionUiState
import io.github.emusute1212.calculator.ui.main.section.operators.OperatorButtonSection
import io.github.emusute1212.calculator.ui.main.section.operators.OperatorButtonSectionUiState
import io.github.emusute1212.calculator.ui.main.section.specials.SpecialsButtonSection
import io.github.emusute1212.calculator.ui.main.section.specials.SpecialsButtonSectionUiState
import io.github.emusute1212.calculator.ui.themes.CalculatorTheme
import java.math.BigDecimal

@Composable
fun CalculatorScreenHost(
    viewModel: CalculatorViewModel = hiltViewModel(),
) {
    val state = rememberCalculatorScreenUiState(
        viewModel = viewModel
    )
    CalculatorScreen(
        state = state,
    )
}

@Composable
private fun CalculatorScreen(
    state: CalculatorScreenUiState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomEnd,
        ) {
            AutoSizeText(
                text = state.calculationHistory.joinToString("") {
                    it.number.toString() + it.operator.text
                },
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.outlineVariant,
                ),
                textAlign = AutoTextAlign.TextEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp,
                    ),
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        AutoSizeText(
            text = state.displayText,
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.outline,
            ),
            maxLine = 1,
            textAlign = AutoTextAlign.TextEnd,
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .padding(
                    horizontal = 12.dp,
                ),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.onBackground,
                )
                .padding(
                    top = 36.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 28.dp,
                ),
            horizontalArrangement = Arrangement.Center,
        ) {
            Column {
                SpecialsButtonSection(
                    state = state.specialsButtonSectionUiState
                )
                Spacer(modifier = Modifier.height(16.dp))
                NumberButtonSection(
                    state = state.numberButtonSectionUiState
                )
            }
            Spacer(modifier = Modifier.width(24.dp))
            OperatorButtonSection(
                state = state.operatorButtonSectionUiState
            )
        }
    }
}

//@Composable
//private fun AutoSizeTextTypeText(
//    text: String,
//    maxFontSize: TextUnit,
//    maxLine: Int = Int.MAX_VALUE,
//    style: TextStyle,
//    modifier: Modifier,
//) {
//    var textSize by remember(text) { mutableStateOf(maxFontSize) }
//    Text(
//        text = text,
//        style = style.copy(
//            fontSize = textSize,
//        ),
//        maxLines = maxLine,
//        overflow = TextOverflow.Ellipsis,
//        modifier = modifier,
//        onTextLayout = {
//            if (it.hasVisualOverflow) {
//                textSize = (textSize.value - 1.0F).sp
//            }
//        },
//    )
//}

@Preview
@Composable
fun PreviewCalculatorScreen() {
    CalculatorTheme {
        CalculatorScreen(
            state = CalculatorScreenUiState(
                calculationHistory = listOf(
                    CalcEntity(
                        number = BigDecimal(2),
                        operator = Controller.Operators.Minus
                    ),
                    CalcEntity(
                        number = BigDecimal(1),
                        operator = Controller.Operators.Minus
                    )
                ),
                displayText = "123,456,789",
                numberButtonSectionUiState = NumberButtonSectionUiState(
                    onAction = {}
                ),
                operatorButtonSectionUiState = OperatorButtonSectionUiState(
                    onAction = {}
                ),
                specialsButtonSectionUiState = SpecialsButtonSectionUiState(
                    canAllClear = true,
                    onAction = {}
                )
            )
        )
    }
}
