package io.github.emusute1212.calculator.ui.main.section.numbers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.emusute1212.calculator.model.entity.Controller
import io.github.emusute1212.calculator.ui.main.CalculatorAction
import io.github.emusute1212.calculator.ui.themes.CalculatorTheme

@Composable
fun NumberButtonSection(
    state: NumberButtonSectionUiState,
) {
    val buttonPadding = 16.dp
    Column(
        verticalArrangement = Arrangement.spacedBy(buttonPadding),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(buttonPadding),
        ) {
            NumberButton(
                number = Controller.Numbers.Seven,
                onClick = {
                    state.onAction(
                        CalculatorAction.OnClickControllerButton(
                            controller = it
                        )
                    )
                }
            )
            NumberButton(
                number = Controller.Numbers.Eight,
                onClick = {
                    state.onAction(
                        CalculatorAction.OnClickControllerButton(
                            controller = it
                        )
                    )
                }
            )
            NumberButton(
                number = Controller.Numbers.Nine,
                onClick = {
                    state.onAction(
                        CalculatorAction.OnClickControllerButton(
                            controller = it
                        )
                    )
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(buttonPadding),
        ) {
            NumberButton(
                number = Controller.Numbers.Four,
                onClick = {
                    state.onAction(
                        CalculatorAction.OnClickControllerButton(
                            controller = it
                        )
                    )
                }
            )
            NumberButton(
                number = Controller.Numbers.Five,
                onClick = {
                    state.onAction(
                        CalculatorAction.OnClickControllerButton(
                            controller = it
                        )
                    )
                }
            )
            NumberButton(
                number = Controller.Numbers.Six,
                onClick = {
                    state.onAction(
                        CalculatorAction.OnClickControllerButton(
                            controller = it
                        )
                    )
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(buttonPadding),
        ) {
            NumberButton(
                number = Controller.Numbers.One,
                onClick = {
                    state.onAction(
                        CalculatorAction.OnClickControllerButton(
                            controller = it
                        )
                    )
                }
            )
            NumberButton(
                number = Controller.Numbers.Two,
                onClick = {
                    state.onAction(
                        CalculatorAction.OnClickControllerButton(
                            controller = it
                        )
                    )
                }
            )
            NumberButton(
                number = Controller.Numbers.Three,
                onClick = {
                    state.onAction(
                        CalculatorAction.OnClickControllerButton(
                            controller = it
                        )
                    )
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            NumberButton(
                width = 142.dp,
                number = Controller.Numbers.Zero,
                onClick = {
                    state.onAction(
                        CalculatorAction.OnClickControllerButton(
                            controller = it
                        )
                    )
                }
            )
            NumberButton(
                number = Controller.Numbers.Dot,
                onClick = {
                    state.onAction(
                        CalculatorAction.OnClickControllerButton(
                            controller = it
                        )
                    )
                }
            )
        }
    }
}

@Composable
private fun NumberButton(
    width: Dp = 64.dp,
    number: Controller.Numbers,
    onClick: (Controller.Numbers) -> Unit
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(64.dp)
            .clip(RoundedCornerShape(percent = 36))
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                onClick(number)
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = number.text,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface,
            ),
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
fun PreviewNumberButtonSection() {
    CalculatorTheme {
        NumberButtonSection(
            state = NumberButtonSectionUiState(
                onAction = {}
            )
        )
    }
}
