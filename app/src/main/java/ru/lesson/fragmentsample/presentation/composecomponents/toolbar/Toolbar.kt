package ru.lesson.fragmentsample.presentation.composecomponents.toolbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ru.lesson.fragmentsample.presentation.composecomponents.AppTheme
import ru.lesson.fragmentsample.presentation.composecomponents.FragmentSampleTheme

@Composable
fun Toolbar(
    title: String,
    subtitle: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    isBackArrowVisible: Boolean = true,
    onBackClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.zIndex(1f),
        title = {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    style = AppTheme.typography.h6
                )
                if (subtitle?.isNotBlank() == true) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = subtitle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = AppTheme.typography.body1,
                        color = Color.Gray
                    )
                }

            }
        },
        actions = actions,
        navigationIcon = {
            if (isBackArrowVisible)
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
        },
        backgroundColor = AppTheme.colors.secondary,
        contentColor = AppTheme.colors.onPrimary,
        elevation = elevation
    )
}

@Preview(name = "Toolbar", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PToolbarPreview() {
    FragmentSampleTheme {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Toolbar(
                title = "title",
                subtitle = "subtitle",
                actions = {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null
                    )
                },
                onBackClick = {}
            )

            Toolbar(
                title = "title without subtitle",
                actions = {
                    Text(text = "action", color = Color.Green)
                },
                onBackClick = {}
            )
            Toolbar(
                title = "title without action",
                onBackClick = {}
            )
            Toolbar(
                title = "just title",
                isBackArrowVisible = false,
                onBackClick = {}
            )
        }
    }
}
