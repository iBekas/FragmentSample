package ru.lesson.fragmentsample.presentation.recycler

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import ru.lesson.fragmentsample.R
import ru.lesson.fragmentsample.presentation.composecomponents.AppTheme
import ru.lesson.fragmentsample.presentation.composecomponents.ComposeFragment
import ru.lesson.fragmentsample.presentation.composecomponents.FIRST_THEME
import ru.lesson.fragmentsample.presentation.composecomponents.FragmentSampleTheme
import ru.lesson.fragmentsample.presentation.composecomponents.SECOND_THEME
import ru.lesson.fragmentsample.presentation.composecomponents.dialogs.DefaultDialog
import ru.lesson.fragmentsample.presentation.composecomponents.dialogs.ItemsDialog
import ru.lesson.fragmentsample.presentation.composecomponents.toolbar.Toolbar
import ru.lesson.fragmentsample.presentation.detail.DetailFragment
import ru.lesson.fragmentsample.presentation.model.ExampleModel
import ru.lesson.fragmentsample.presentation.pokemons.pokemonlist.PokemonsListFragment


class RecyclerFragment : ComposeFragment() {

    private val viewModel: RecyclerViewModel by lazy {
        ViewModelProvider(this)[RecyclerViewModel::class.java]
    }

    @Composable
    override fun GetContent() {
        val state = viewModel.viewStateObs.observeAsState().value ?: return

        FragmentSampleTheme(themeCode = state.currentTheme) { RecyclerScreen(state) }
    }

    @Composable
    private fun RecyclerScreen(state: RecyclerViewState) {

        if (state.errorText.isNotBlank())
            Toast.makeText(context, state.errorText, Toast.LENGTH_SHORT).show()

        if (state.isShowDeleteDialog) DeleteDialog(state.deletableItemId)

        if (state.isShowSettingsDialog) SettingsDialog()

        Column(modifier = Modifier.background(AppTheme.colors.background)) {

            Toolbar(
                title = stringResource(id = R.string.recycler_fragment),
                isBackArrowVisible = false,
                actions = {
                    IconButton(onClick = {
                        viewModel.submitUIEvent(RecyclerEvent.ShowSettingsDialog(true))
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        requireActivity()
                            .supportFragmentManager
                            .beginTransaction()
                            .replace(
                                R.id.fragment_container,
                                PokemonsListFragment()
                            )
                            .addToBackStack("")
                            .commit()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ImageSearch,
                            contentDescription = null
                        )
                    }
                },
                onBackClick = { }
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {

                items(
                    items = state.itemList,
                    itemContent = { item ->
                        //При разных типах item
                        // when(item.type) {
                        //TYPE_NOTE -> Item(item)
                        //TYPE_BIRTHDAY -> SomeItem(item)
                        // }
                        Item(item)
                    }
                )
            }

        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {

            FloatingActionButton(
                modifier = Modifier
                    .padding(
                        end = AppTheme.dimens.sideMargin,
                        bottom = AppTheme.dimens.sideMargin
                    ),
                backgroundColor = AppTheme.colors.secondary,
                onClick = { goToDetails() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_black),
                    contentDescription = null,
                    tint = AppTheme.colors.background
                )
            }
        }

    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun Item(model: ExampleModel) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.contentMargin)
                .combinedClickable(
                    onClick = { goToDetails(model) },
                    onLongClick = { viewModel.submitUIEvent(RecyclerEvent.ShowDeleteDialog(true, model.id)) },
                ),
            shape = RoundedCornerShape(AppTheme.dimens.contentMargin),
            backgroundColor = AppTheme.colors.primary,
            elevation = AppTheme.dimens.halfContentMargin
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.contentMargin)
            ) {
                Text(text = model.name, style = AppTheme.typography.h6)
                Text(text = model.description, style = AppTheme.typography.body1)
            }
        }
    }

    @Composable
    private fun DeleteDialog(id: Long) {
        DefaultDialog(
            title = stringResource(id = R.string.delete),
            onPositiveClick = {
                if (id != -1L) viewModel.submitUIEvent(RecyclerEvent.DeleteItem(id))
                viewModel.submitUIEvent(RecyclerEvent.ShowDeleteDialog(false, -1))
            },
            onNegativeClick = { viewModel.submitUIEvent(RecyclerEvent.ShowDeleteDialog(false, -1)) }) {
        }
    }

    @Composable
    private fun SettingsDialog() {

        val items = arrayOf(
            stringResource(id = R.string.first_theme),
            stringResource(id = R.string.second_theme)
        )

        ItemsDialog(
            title = stringResource(R.string.set_theme),
            items = items,
            onItemClick = { position ->
                when (position) {
                    0 -> viewModel.submitUIEvent(RecyclerEvent.SetTheme(FIRST_THEME))
                    1 -> viewModel.submitUIEvent(RecyclerEvent.SetTheme(SECOND_THEME))
                }
            }
        ) { viewModel.submitUIEvent(RecyclerEvent.ShowSettingsDialog(false)) }
    }

    private fun goToDetails(model: ExampleModel? = null) {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                DetailFragment.newInstance(model ?: viewModel.viewState.getEmptyItem())
            )
            .addToBackStack("")
            .commit()
    }

    @Preview(name = "RecyclerScreen", uiMode = Configuration.UI_MODE_NIGHT_NO)
    @Composable
    private fun RecyclerScreenPreview() {
        FragmentSampleTheme {

            val model = ExampleModel(
                id = 0,
                name = "Заметка",
                description = "Ты собака, я собака, ты собака, я собака, ты собака, я собака, ты собака, " +
                        "я собака, ты собака, я собака, ты собака, я собака, ты собака, я собака"
            )

            val state = RecyclerViewState(
                itemList = listOf(model, model, model),
                isShowDeleteDialog = false,
                isLoading = false,
                errorText = ""

            )

            RecyclerScreen(state)
        }
    }

}
