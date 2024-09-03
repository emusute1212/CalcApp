package io.github.emusute1212.calculator.ui.main.section.specials

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.emusute1212.calculator.model.entity.Controller
import io.github.emusute1212.calculator.ui.main.CalculatorAction
import io.github.emusute1212.calculator.ui.themes.CalculatorTheme

@Composable
fun SpecialsButtonSection(
    state: SpecialsButtonSectionUiState,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SpecialButton(
            special = if (state.canAllClear) {
                Controller.Specials.AllClear
            } else {
                Controller.Specials.Clear
            },
            onClick = {
                state.onAction(
                    CalculatorAction.OnClickControllerButton(
                        controller = it
                    )
                )
            }
        )
        SpecialButton(
            special = Controller.Specials.Switch,
            onClick = {
                state.onAction(
                    CalculatorAction.OnClickControllerButton(
                        controller = it
                    )
                )
            }
        )
        SpecialButton(
            special = Controller.Specials.Percent,
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
private fun SpecialButton(
    special: Controller.Specials,
    onClick: (Controller.Specials) -> Unit,
) {
    Text(
        text = special.text,
        style = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.onSecondary,
        ),
        modifier = Modifier
            .size(size = 64.dp)
            .clip(RoundedCornerShape(percent = 20))
            .background(MaterialTheme.colorScheme.secondary)
            .clickable {
                onClick(special)
            }
            .wrapContentSize(Alignment.Center),
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
private fun PreviewSpecialsButtonSectionForAllClear() {
    CalculatorTheme {
        SpecialsButtonSection(
            state = SpecialsButtonSectionUiState(
                canAllClear = true,
                onAction = {}
            )
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
private fun PreviewSpecialsButtonSectionForClear() {
    CalculatorTheme {
        SpecialsButtonSection(
            state = SpecialsButtonSectionUiState(
                canAllClear = false,
                onAction = {}
            )
        )
    }
}
