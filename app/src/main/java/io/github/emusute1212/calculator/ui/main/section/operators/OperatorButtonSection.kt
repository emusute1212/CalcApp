package io.github.emusute1212.calculator.ui.main.section.operators

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.emusute1212.calculator.model.entity.Controller
import io.github.emusute1212.calculator.ui.main.CalculatorAction
import io.github.emusute1212.calculator.ui.themes.CalculatorTheme

@Composable
fun OperatorButtonSection(
    state: OperatorButtonSectionUiState
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        OperatorButton(
            operator = Controller.Operators.Divide,
            onClick = {
                state.onAction(
                    CalculatorAction.OnClickControllerButton(
                        controller = it
                    )
                )
            }
        )
        OperatorButton(
            operator = Controller.Operators.Times,
            onClick = {
                state.onAction(
                    CalculatorAction.OnClickControllerButton(
                        controller = it
                    )
                )
            }
        )
        OperatorButton(
            operator = Controller.Operators.Minus,
            onClick = {
                state.onAction(
                    CalculatorAction.OnClickControllerButton(
                        controller = it
                    )
                )
            }
        )
        OperatorButton(
            operator = Controller.Operators.Plus,
            onClick = {
                state.onAction(
                    CalculatorAction.OnClickControllerButton(
                        controller = it
                    )
                )
            }
        )
        OperatorButton(
            backgroundColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            operator = Controller.Operators.Equal,
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

@Composable
private fun OperatorButton(
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    contentColor: Color = MaterialTheme.colorScheme.onSecondary,
    operator: Controller.Operators,
    onClick: (Controller.Operators) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(size = 64.dp)
            .clip(RoundedCornerShape(percent = 20))
            .background(backgroundColor)
            .clickable {
                onClick(operator)
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = operator.text,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = contentColor,
            ),
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
private fun PreviewOperatorButtonSection() {
    CalculatorTheme {
        OperatorButtonSection(
            state = OperatorButtonSectionUiState(
                onAction = {}
            )
        )
    }
}