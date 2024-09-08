package io.github.emusute1212.calculator.ui.component

import android.graphics.Typeface
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.TextViewCompat

// It is not officially supported, so use TextView here.
@Composable
fun AutoSizeText(
    text: String,
    maxLine: Int = Int.MAX_VALUE,
    style: TextStyle,
    textGravity: TextGravity,
    modifier: Modifier,
) {
    val resolver = LocalFontFamilyResolver.current
    val typeface = remember(resolver, style) {
        resolver.resolve(
            fontFamily = style.fontFamily,
            fontWeight = style.fontWeight ?: FontWeight.Normal,
            fontStyle = style.fontStyle ?: FontStyle.Normal,
            fontSynthesis = style.fontSynthesis ?: FontSynthesis.All,
        ).value as Typeface
    }
    AndroidView(
        factory = { context ->
            TextView(context).apply {
                this.text = text
                setTextColor(style.color.toArgb())
                this.typeface = typeface
                maxLines = maxLine
                gravity = textGravity.gravity
                ellipsize = TextUtils.TruncateAt.END
                TextViewCompat.setAutoSizeTextTypeWithDefaults(
                    this,
                    TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM,
                )
                TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                    this,
                    10.sp.value.toInt(),
                    style.fontSize.value.toInt(),
                    1,
                    TypedValue.COMPLEX_UNIT_SP,
                )
            }
        },
        update = {
            it.text = text
        },
        modifier = modifier
            .fillMaxWidth()
    )
}

enum class TextGravity(
    val gravity: Int
) {
    Center(Gravity.CENTER),
    CenterHorizontal(Gravity.CENTER_HORIZONTAL),
    CenterVertical(Gravity.CENTER_VERTICAL),
    TopEnd(Gravity.TOP or Gravity.END),
    TopStart(Gravity.TOP or Gravity.START),
    BottomEnd(Gravity.BOTTOM or Gravity.END),
    BottomStart(Gravity.BOTTOM or Gravity.START),
}