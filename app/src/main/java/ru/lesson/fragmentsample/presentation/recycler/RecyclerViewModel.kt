package ru.lesson.fragmentsample.presentation.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.lesson.fragmentsample.data.repository.ItemRepository
import ru.lesson.fragmentsample.data.repository.ItemRepositoryImpl
import ru.lesson.fragmentsample.presentation.model.ExampleModel
import ru.lesson.fragmentsample.presentation.model.Mapper


class RecyclerViewModel(
    private val itemRepository: ItemRepository = ItemRepositoryImpl()
) : ViewModel() {

    private val _viewState = MutableLiveData(RecyclerViewState())
    val viewStateObs: LiveData<RecyclerViewState> get() = _viewState
    private var viewState: RecyclerViewState
        get() = _viewState.value!!
        set(value) {
            _viewState.value = value
        }

    fun submitUIEvent(event: RecyclerEvent) {
        handleUIEvent(event)
    }

    private fun handleUIEvent(event: RecyclerEvent) {
        when (event) {
            RecyclerEvent.GetItems -> getListItems()
            is RecyclerEvent.AddItem -> addNewItem(item = event.item)
        }
    }

    //Все функции viewModel приватные, кроме submitUIEvent
    private fun getListItems() {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            delay(1500)
            viewState = viewState.copy(
                itemList = Mapper.transformToPresentation(itemRepository.getItems()),
                isLoading = false
            )
        }
    }

    private fun addNewItem(item: ExampleModel) {
        val currentItems = viewState.itemList
        viewState = viewState.copy(itemList = currentItems + item)
    }

}
