package ru.lesson.fragmentsample.presentation.detail

import android.content.res.Configuration
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import ru.lesson.fragmentsample.R
import ru.lesson.fragmentsample.presentation.composecomponents.AppTheme
import ru.lesson.fragmentsample.presentation.composecomponents.ComposeFragment
import ru.lesson.fragmentsample.presentation.composecomponents.FragmentSampleTheme
import ru.lesson.fragmentsample.presentation.composecomponents.buttons.PrimaryButton
import ru.lesson.fragmentsample.presentation.composecomponents.toolbar.Toolbar
import ru.lesson.fragmentsample.presentation.model.ExampleModel

class DetailFragment : ComposeFragment() {

    companion object {
        private const val KEY = "KEY"

        fun newInstance(item: ExampleModel) = DetailFragment().apply {
            arguments = bundleOf(KEY to item)
        }
    }

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    @Composable
    override fun GetContent() {

        val item =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) requireArguments().getParcelable(
                KEY,
                ExampleModel::class.java
            ) ?: getEmptyItem() else requireArguments().getParcelable(KEY) ?: getEmptyItem()

        viewModel.submitUIEvent(DetailEvent.SetItem(item))

        val exampleModel = viewModel.exampleModel.observeAsState().value ?: return
        val currentTheme = viewModel.currentTheme.observeAsState().value ?: return
        val exit = viewModel.exit.observeAsState().value ?: return

        FragmentSampleTheme(themeCode = currentTheme) {
            DetailScreen(exampleModel, exit)
        }
    }

    @Composable
    private fun DetailScreen(item: ExampleModel, exit: Boolean) {

        if (exit) goBack()

        var currentName by remember { mutableStateOf("") }
        currentName = currentName.ifBlank { item.name }

        var currentDescription by remember { mutableStateOf("") }
        currentDescription = currentDescription.ifBlank { item.description }


        Column(modifier = Modifier.background(AppTheme.colors.background)) {

            Toolbar(
                title = stringResource(id = R.string.detail_fragment),
                onBackClick = { goBack() }
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = currentName,
                onValueChange = {
                    currentName = it
                    item.name = it
                    viewModel.submitUIEvent(DetailEvent.SetItem(item))
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.label_hint),
                        style = AppTheme.typography.body1
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = AppTheme.colors.secondary
                ),
            )
            TextField(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                value = currentDescription,
                onValueChange = {
                    currentDescription = it
                    item.description = it
                    viewModel.submitUIEvent(DetailEvent.SetItem(item))
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.description_hint),
                        style = AppTheme.typography.body1
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = AppTheme.colors.secondary
                ),
            )

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.save),
                isEnabled = currentName.isNotBlank() && currentDescription.isNotBlank()
            ) {
                viewModel.submitUIEvent(DetailEvent.SaveItem(item.id))
            }

        }

    }

    private fun goBack() = requireActivity().supportFragmentManager.popBackStack()

    private fun getEmptyItem(): ExampleModel {
        return ExampleModel(
            id = 0,
            name = "",
            description = ""
        )
    }

    @Preview(name = "DetailScreen", uiMode = Configuration.UI_MODE_NIGHT_NO)
    @Composable
    private fun DetailScreenPreview() {
        FragmentSampleTheme {

            val model = ExampleModel(
                id = 0,
                name = "Заметка про Витю",
                description = "Витя, ты справишься! Ты собака, я собака, ты собака, " +
                        "я собака, ты собака, я собака, ты собака.",
            )

            DetailScreen(
                item = model,
                exit = false
            )
        }
    }

}
