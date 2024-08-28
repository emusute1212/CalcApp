package io.github.emusute1212.calculator.ui.component

import android.graphics.Typeface
import android.text.TextUtils
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.View.TEXT_ALIGNMENT_GRAVITY
import android.view.View.TEXT_ALIGNMENT_INHERIT
import android.view.View.TEXT_ALIGNMENT_TEXT_END
import android.view.View.TEXT_ALIGNMENT_TEXT_START
import android.view.View.TEXT_ALIGNMENT_VIEW_END
import android.view.View.TEXT_ALIGNMENT_VIEW_START
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.TextViewCompat

// It is not officially supported, so use TextView here.
@Composable
fun AutoSizeText(
    text: String,
    maxLine: Int = Int.MAX_VALUE,
    style: TextStyle,
    textAlign: AutoTextAlign,
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
                textSize = style.fontSize.value
                setTextColor(style.color.toArgb())
                this.typeface = typeface
                maxLines = maxLine
                textAlignment = textAlign.alignment
                ellipsize = TextUtils.TruncateAt.END
                TextViewCompat.setAutoSizeTextTypeWithDefaults(
                    this,
                    TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM,
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

enum class AutoTextAlign(
    val alignment: Int
) {
    Inherit(TEXT_ALIGNMENT_INHERIT),
    Gravity(TEXT_ALIGNMENT_GRAVITY),
    TextStart(TEXT_ALIGNMENT_TEXT_START),
    TextEnd(TEXT_ALIGNMENT_TEXT_END),
    Center(TEXT_ALIGNMENT_CENTER),
    ViewStart(TEXT_ALIGNMENT_VIEW_START),
    ViewEnd(TEXT_ALIGNMENT_VIEW_END),
}