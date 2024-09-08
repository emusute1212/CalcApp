package io.github.emusute1212.calculator.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.emusute1212.calculator.R
import io.github.emusute1212.calculator.ui.component.CalculatorDrawerContent
import io.github.emusute1212.calculator.ui.component.DrawerContent
import io.github.emusute1212.calculator.ui.main.CalculatorScreenRoute
import io.github.emusute1212.calculator.ui.main.calculatorScreen
import io.github.emusute1212.calculator.ui.menus.OssLicenseScreenRoute
import io.github.emusute1212.calculator.ui.menus.ossLicenseScreen
import kotlinx.coroutines.launch

@Composable
fun CalculatorApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        CalculatorNavHost()
    }
}

@Composable
private fun CalculatorNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val fadeDurationMillis = 400
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        enterTransition = {
            fadeIn(
                animationSpec = tween(fadeDurationMillis)
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(fadeDurationMillis)
            )
        },
        startDestination = CalculatorScreenRoute,
    ) {
        calculatorScreen(
            navController = navController,
        )
        ossLicenseScreen(
            navController = navController,
        )
    }
}