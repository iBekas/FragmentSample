package ru.lesson.fragmentsample.presentation.composecomponents.dialogs

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.lesson.fragmentsample.presentation.composecomponents.AppTheme
import ru.lesson.fragmentsample.presentation.composecomponents.FragmentSampleTheme
import ru.lesson.fragmentsample.presentation.composecomponents.buttons.DialogButton

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemsDialog(
    title: String = "",
    subTitle: String = "",
    negativeButtonText: String = "",
    isEnabled: Boolean = true,
    items: Array<String>,
    onItemClick: (Int) -> Unit,
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
                    top = if (title.isBlank() && subTitle.isBlank()) 0.dp else AppTheme.dimens.sideMargin,
                    bottom = if (negativeButtonText.isNotBlank()) 0.dp else AppTheme.dimens.sideMargin,
                    start = AppTheme.dimens.sideMargin,
                    end = AppTheme.dimens.sideMargin
                )
        ) {
            if (title.isNotBlank())
                Text(
                    text = title,
                    style = AppTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = AppTheme.dimens.halfContentMargin)
                )


            if (subTitle.isNotBlank())
                Text(text = subTitle, style = AppTheme.typography.caption, color = AppTheme.colors.notEnabled)

            (items.indices).forEach { index ->

                CompositionLocalProvider(
                    LocalMinimumInteractiveComponentEnforcement provides false,
                ) {
                    TextButton(
                        onClick = { onItemClick.invoke(index)},
                        enabled = isEnabled,
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = if (index == 0) AppTheme.dimens.contentMargin
                                else AppTheme.dimens.halfContentMargin
                            )
                    ) {
                        Text(
                            text = items[index],
                            style = AppTheme.typography.body1,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }

            if (negativeButtonText.isNotBlank())
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    DialogButton(
                        text = negativeButtonText,
                        onClick = { dismiss.invoke() }
                    )
                }
        }
    }
}

@Preview(name = "ItemsDialog", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ItemsDialogPreview() {
    FragmentSampleTheme() {
        ItemsDialog(
            "Добавьте фото",
            "При добавлении файлов, пользователь должен учитывать ограничения, все добавленные файлы не должны превышать 10 Мб.",
            "Закрыть",
            true,
            arrayOf("Добавить фото", "Добавить видео", "Выбрать файл"),
            {},
            {}
        )
    }
}
