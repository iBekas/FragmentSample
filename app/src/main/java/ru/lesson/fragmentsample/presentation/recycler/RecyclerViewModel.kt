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
    //Создаем экземпляр именно интерфейса ItemRepository, но инициализируем классом, который его имплемиентирует,
    // так мы сможешь в любой момент изменить реализацию, почти не затрагивая слой презентации
    private val itemRepository: ItemRepository = ItemRepositoryImpl()
) : ViewModel() {

    //Инициализируем нашу LiveData, именно за ее изменениями следит наша View(фрагмент)
    //Про LiveData на самостоятельно изучение
    //В данном случае мы кладет туда RecyclerViewState - наш класс данных, но тут может и просто Booleen или String
    // private val _viewStateLoading = MutableLiveData(Boolean)- как пример
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
            //Обработка ивента типа object
            RecyclerEvent.GetItems -> getListItems()
            //Обработка ивента типа class
            is RecyclerEvent.AddItem -> addNewItem(item = event.item)
        }
    }

    //Все функции viewModel приватные, кроме submitUIEvent
    private fun getListItems() {
        // Не обращай внимания на viewModelScope.launch, это обсудим позже, пока пусть будет магией
        // Нам это нужно сейчас чтобы задержку к 1.5 секунды поставить, для имитации загрузки
        viewModelScope.launch {
            // Запускаем отображение нашей загрузки
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
