package ru.lesson.fragmentsample.presentation.composecomponents.dialogs

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.lesson.fragmentsample.presentation.composecomponents.AppTheme
import ru.lesson.fragmentsample.presentation.composecomponents.FragmentSampleTheme

@Composable
fun LoaderBlock(text: String = "Идет обмен данными с сервером") {
    Dialog(
        onDismissRequest = { },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(
                    color = AppTheme.colors.primary,
                    shape = RoundedCornerShape(AppTheme.dimens.sideMargin)
                )
                .padding(AppTheme.dimens.sideMargin)
        ) {
            CircularProgressIndicator(color = AppTheme.colors.secondary)
            Text(
                modifier = Modifier.padding(start = AppTheme.dimens.sideMargin),
                text = text,
            )
        }
    }
}

@Preview(name = "LoaderBlock", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun LoaderBlockPreview() {
    FragmentSampleTheme {
        LoaderBlock("Идет обмен данными с сервером")
    }
}
