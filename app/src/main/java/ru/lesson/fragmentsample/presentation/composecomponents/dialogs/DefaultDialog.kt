package ru.lesson.fragmentsample.presentation.composecomponents.dialogs

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.lesson.fragmentsample.presentation.composecomponents.AppTheme
import ru.lesson.fragmentsample.presentation.composecomponents.buttons.DialogButton
import ru.lesson.fragmentsample.R
import ru.lesson.fragmentsample.presentation.composecomponents.FragmentSampleTheme


@Composable
fun DefaultDialog(
    title: String,
    massage: String = "",
    negativeButtonText: String = "",
    positiveButtonText: String = "",
    negativeButtonColor: Color? = null,
    positiveButtonColor: Color? = null,
    isEnabled: Boolean = true,
    onPositiveClick: () -> Unit,
    onNegativeClick: (() -> Unit)? = null,
    dismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { dismiss.invoke() },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = true)
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = AppTheme.colors.primary,
                    shape = RoundedCornerShape(AppTheme.dimens.sideMargin)
                )
                .padding(
                    end = AppTheme.dimens.sideMargin,
                    start = AppTheme.dimens.sideMargin,
                    top = AppTheme.dimens.sideMargin
                )
        ) {
            Text(
                text = title,
                style = AppTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(bottom = AppTheme.dimens.sideMargin)
                    .fillMaxWidth()
            )

            if (massage.isNotBlank())
                Text(text = massage, style = AppTheme.typography.body1, modifier = Modifier.fillMaxWidth())

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = AppTheme.dimens.contentMargin),
                horizontalArrangement = Arrangement.End,
            ) {
                DialogButton(
                    text = negativeButtonText.ifBlank { stringResource(R.string.no) },
                    isEnabled = isEnabled,
                    onClick = { if (onNegativeClick != null) onNegativeClick.invoke() else dismiss.invoke() },
                    color = negativeButtonColor ?: AppTheme.colors.secondary
                )
                DialogButton(
                    text = positiveButtonText.ifBlank { stringResource(R.string.yes) },
                    isEnabled = isEnabled,
                    onClick = { onPositiveClick.invoke() },
                    color = positiveButtonColor ?: AppTheme.colors.secondary
                )
            }
        }
    }
}

@Preview(name = "DefaultDialog", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun DefaultDialogPreview() {
    FragmentSampleTheme() {
        DefaultDialog(
            title = "Уверен?",
            massage = "Раз уверен жми. Но только аккуратно. Пока жмешь, проверим как смотрится длинный текст.",
            onPositiveClick = {}
        ) {}
    }
}
