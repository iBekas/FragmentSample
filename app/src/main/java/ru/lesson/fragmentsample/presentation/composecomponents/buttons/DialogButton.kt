package ru.lesson.fragmentsample.presentation.composecomponents.buttons


import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lesson.fragmentsample.presentation.composecomponents.AppTheme
import ru.lesson.fragmentsample.presentation.composecomponents.FragmentSampleTheme


@Composable
fun DialogButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    color: Color = AppTheme.colors.secondary,
    textAlign: TextAlign = TextAlign.End,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier.width(IntrinsicSize.Min),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = color,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = AppTheme.colors.notEnabled,
        ),
        enabled = isEnabled,
        elevation = null,
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
    )
    {
        Text(
            style = AppTheme.typography.subtitle1,
            text = text,
            textAlign = textAlign,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(name = "DialogButton", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun DialogButtonPreview() {
    FragmentSampleTheme() {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            DialogButton(text = "Text") {}
            DialogButton(text = "Text", isEnabled = false) {}
        }
    }
}
