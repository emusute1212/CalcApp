package io.github.emusute1212.calculator.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.emusute1212.calculator.R

@Composable
fun CalculatorDrawerContent(
    onClickContent: (drawerContent: DrawerContent) -> Unit,
) {
    ModalDrawerSheet {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(16.dp),
        )
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onSurface,
        )
        DrawerContent.entries.forEach {
            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(id = R.string.open_source_license),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(16.dp),
                    )
                },
                selected = false,
                onClick = {
                    onClickContent(it)
                },
            )
        }
    }
}

enum class DrawerContent(
    @StringRes
    val displayText: Int
) {
    LICENSE(R.string.app_name)
}