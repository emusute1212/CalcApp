package io.github.emusute1212.calculator.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.emusute1212.calculator.R
import io.github.emusute1212.calculator.model.entity.CalcEntity
import io.github.emusute1212.calculator.model.entity.Controller
import io.github.emusute1212.calculator.ui.component.AutoSizeText
import io.github.emusute1212.calculator.ui.component.AutoTextAlign
import io.github.emusute1212.calculator.ui.component.CalculatorDrawerContent
import io.github.emusute1212.calculator.ui.component.DrawerContent
import io.github.emusute1212.calculator.ui.main.section.numbers.NumberButtonSection
import io.github.emusute1212.calculator.ui.main.section.numbers.NumberButtonSectionUiState
import io.github.emusute1212.calculator.ui.main.section.operators.OperatorButtonSection
import io.github.emusute1212.calculator.ui.main.section.operators.OperatorButtonSectionUiState
import io.github.emusute1212.calculator.ui.main.section.specials.SpecialsButtonSection
import io.github.emusute1212.calculator.ui.main.section.specials.SpecialsButtonSectionUiState
import io.github.emusute1212.calculator.ui.menus.OssLicenseScreenRoute
import io.github.emusute1212.calculator.ui.themes.CalculatorTheme
import kotlinx.coroutines.launch
import java.math.BigDecimal

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
            ),
            paddingValues = PaddingValues(0.dp),
        )
    }
}
