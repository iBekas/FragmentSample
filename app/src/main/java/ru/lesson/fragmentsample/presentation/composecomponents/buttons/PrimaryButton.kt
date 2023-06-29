package ru.lesson.fragmentsample.presentation.composecomponents.buttons

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.lesson.fragmentsample.presentation.composecomponents.AppTheme
import ru.lesson.fragmentsample.presentation.composecomponents.FragmentSampleTheme


@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    minWidth: Dp = 180.dp,
    minHeight: Dp = 48.dp,
    color: Color = AppTheme.colors.secondary,
    bottomPadding: Dp = AppTheme.dimens.sideMargin,
    onClick: () -> Unit,
) {
    with(AppTheme.dimens) {
        Row(
            modifier = modifier
                .padding(bottom = bottomPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            val shape = RoundedCornerShape(contentMargin)

            Button(
                modifier = Modifier
                    .widthIn(min = minWidth)
                    .heightIn(min = minHeight)
                    .border(
                        width = 2.dp,
                        color = if (isEnabled) {
                            color
                        } else {
                            AppTheme.colors.notEnabled
                        },
                        shape = shape
                    ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = AppTheme.colors.primary,
                    contentColor = color,
                    disabledBackgroundColor = AppTheme.colors.primary,
                    disabledContentColor = AppTheme.colors.notEnabled,
                ),
                shape = shape,
                enabled = isEnabled,
                onClick = { onClick.invoke() },
            )
            {
                Text(
                    fontSize = 18.sp,
                    style = AppTheme.typography.subtitle1,
                    text = text,
                )
            }

        }
    }
}

@Preview(name = "PrimaryButton", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PrimaryButtonPreview() {
    FragmentSampleTheme {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            PrimaryButton(modifier = Modifier.fillMaxWidth(), text = "Text") {}
            PrimaryButton(text = "Text", isEnabled = false) {}
            PrimaryButton(text = "Text", color = AppTheme.colors.error) {}
        }
    }
}

