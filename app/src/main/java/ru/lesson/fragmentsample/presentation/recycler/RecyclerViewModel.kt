package ru.lesson.fragmentsample.presentation.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lesson.fragmentsample.app.App
import ru.lesson.fragmentsample.data.repository.ItemRepository
import ru.lesson.fragmentsample.data.repository.ItemRepositoryImpl
import ru.lesson.fragmentsample.presentation.model.Mapper
import ru.lesson.fragmentsample.util.Resource


class RecyclerViewModel(
    private val itemRepository: ItemRepository = ItemRepositoryImpl(App.getExampleDao())
) : ViewModel() {

    private val _viewState = MutableLiveData(RecyclerViewState())
    val viewStateObs: LiveData<RecyclerViewState> get() = _viewState
    var viewState: RecyclerViewState
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
            is RecyclerEvent.DeleteItem -> deleteItem(id = event.id)
        }
    }

    private fun getListItems() {
        //Результат выполнения suspend функции можно получить только внутри корутины(скоупа) - viewModelScope.launch
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            // Получаем наши данные, помним, что результат это Resource - успех, ошибка или загрузка
            val result = itemRepository.getItems()
            when (result) {
                //Если успех
                is Resource.Success -> {
                    viewState = viewState.copy(
                        itemList = Mapper.transformToPresentation(result.data ?: emptyList()),
                        isLoading = false
                    )
                }
                //Если ошибка
                is Resource.Error -> {
                    viewState = viewState.copy(isLoading = false, errorText = result.message ?: "")
                }

                else -> {}
            }
        }
    }


    private fun deleteItem(id: Long) {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            val result = itemRepository.deleteExample(id)
            when (result) {
                is Resource.Success -> {
                    getListItems()
                }

                is Resource.Error -> {
                    viewState = viewState.copy(isLoading = false, errorText = result.message ?: "")
                }

                else -> {}
            }
        }
    }

}
