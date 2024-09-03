package io.github.emusute1212.calculator.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.emusute1212.calculator.R
import io.github.emusute1212.calculator.ui.component.AutoSizeText
import io.github.emusute1212.calculator.ui.component.CalculatorDrawerContent
import io.github.emusute1212.calculator.ui.component.DrawerContent
import io.github.emusute1212.calculator.ui.component.TextGravity
import io.github.emusute1212.calculator.ui.main.section.numbers.NumberButtonSection
import io.github.emusute1212.calculator.ui.main.section.numbers.NumberButtonSectionUiState
import io.github.emusute1212.calculator.ui.main.section.operators.OperatorButtonSection
import io.github.emusute1212.calculator.ui.main.section.operators.OperatorButtonSectionUiState
import io.github.emusute1212.calculator.ui.main.section.specials.SpecialsButtonSection
import io.github.emusute1212.calculator.ui.main.section.specials.SpecialsButtonSectionUiState
import io.github.emusute1212.calculator.ui.menus.OssLicenseScreenRoute
import io.github.emusute1212.calculator.ui.themes.CalculatorTheme
import kotlinx.coroutines.launch

const val CalculatorScreenRoute = "calculator_screen"
fun NavGraphBuilder.calculatorScreen(
    navController: NavController,
) {
    composable(CalculatorScreenRoute) {
        CalculatorScreenHost(
            navController = navController
        )
    }
}

@Composable
private fun CalculatorScreenHost(
    viewModel: CalculatorViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state = rememberCalculatorScreenUiState(
        viewModel = viewModel
    )

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed,
    )
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            CalculatorDrawerContent(
                onClickContent = {
                    when (it) {
                        DrawerContent.LICENSE -> {
                            navController.navigate(
                                route = OssLicenseScreenRoute
                            )
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    }
                }
            )
        },
    ) {
        Scaffold(
            topBar = {
                Image(
                    painter = painterResource(id = R.drawable.ic_menu_black_24dp),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            all = 16.dp,
                        )
                        .clickable {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                )
            },
        ) { padding ->
            CalculatorScreen(
                state = state,
                paddingValues = padding,
            )
        }
    }
}

@Composable
private fun CalculatorScreen(
    state: CalculatorScreenUiState,
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Bottom,
    ) {
        AutoSizeText(
            text = state.calculationHistory,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.outlineVariant,
            ),
            textGravity = TextGravity.BottomEnd,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    horizontal = 12.dp,
                ),
        )
        Spacer(modifier = Modifier.height(12.dp))
        AutoSizeText(
            text = state.displayText,
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.outline,
            ),
            maxLine = 1,
            textGravity = TextGravity.BottomEnd,
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

@Preview(name = "NEXUS_7", device = Devices.NEXUS_7)
@Preview(name = "NEXUS_7_2013", device = Devices.NEXUS_7_2013)
@Preview(name = "NEXUS_5", device = Devices.NEXUS_5)
@Preview(name = "NEXUS_6", device = Devices.NEXUS_6)
@Preview(name = "NEXUS_9", device = Devices.NEXUS_9)
@Preview(name = "NEXUS_10", device = Devices.NEXUS_10)
@Preview(name = "NEXUS_5X", device = Devices.NEXUS_5X)
@Preview(name = "NEXUS_6P", device = Devices.NEXUS_6P)
@Preview(name = "PIXEL_C", device = Devices.PIXEL_C)
@Preview(name = "PIXEL", device = Devices.PIXEL)
@Preview(name = "PIXEL_XL", device = Devices.PIXEL_XL)
@Preview(name = "PIXEL_2", device = Devices.PIXEL_2)
@Preview(name = "PIXEL_2_XL", device = Devices.PIXEL_2_XL)
@Preview(name = "PIXEL_3", device = Devices.PIXEL_3)
@Preview(name = "PIXEL_3_XL", device = Devices.PIXEL_3_XL)
@Preview(name = "PIXEL_3A", device = Devices.PIXEL_3A)
@Preview(name = "PIXEL_3A_XL", device = Devices.PIXEL_3A_XL)
@Preview(name = "PIXEL_4", device = Devices.PIXEL_4)
@Preview(name = "PIXEL_4_XL", device = Devices.PIXEL_4_XL)
@Preview(name = "AUTOMOTIVE_1024p", device = Devices.AUTOMOTIVE_1024p)
@PreviewLightDark
@Composable
fun PreviewCalculatorScreen() {
    CalculatorTheme {
        CalculatorScreen(
            state = CalculatorScreenUiState(
                calculationHistory = "2-1-",
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
            ),
            paddingValues = PaddingValues(0.dp),
        )
    }
}
