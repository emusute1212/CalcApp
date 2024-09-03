package io.github.emusute1212.calculator.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = White800,
    secondary = LightGreen700,
    onSecondary = DarkGreen700,
    tertiary = LightGreen800,
    onTertiary = DarkGreen700,
    surface = White700,
    onSurface = Black500,
    background = White800,
    onBackground = White900,
    outline = Black700,
    outlineVariant = Gray900,
)

private val DarkColorScheme = darkColorScheme(
    primary = Black900,
    secondary = DarkGreen600,
    onSecondary = DarkGreen800,
    tertiary = LightGreen900,
    onTertiary = DarkGreen900,
    surface = Gray800,
    onSurface = White600,
    background = Black800,
    onBackground = Black600,
    outline = White900,
    outlineVariant = White500,
)

@Composable
fun CalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Material color is unused
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
    ) {
        CompositionLocalProvider(
            value = LocalRippleTheme provides object : RippleTheme {
                @Composable
                override fun defaultColor(): Color {
                    return colorScheme.outline
                }

                @Composable
                override fun rippleAlpha(): RippleAlpha {
                    return RippleTheme.defaultRippleAlpha(
                        contentColor = LocalContentColor.current,
                        lightTheme = darkTheme.not()
                    )
                }
            },
            content = content
        )
    }
}